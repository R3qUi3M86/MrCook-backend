package com.codecool.mrcook.service;

import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.comments.RecipeComment;
import com.codecool.mrcook.model.comments.RecipeCommentDTO;
import com.codecool.mrcook.model.recipes.Recipe;
import com.codecool.mrcook.model.votes.RecipeCommentVote;
import com.codecool.mrcook.model.votes.VoteType;
import com.codecool.mrcook.repository.RecipeCommentRepository;
import com.codecool.mrcook.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeCommentService {

    private final RecipeCommentRepository recipeCommentRepository;
    private final RecipeRepository recipeRepository;

    public void createRecipeComment(RecipeComment comment, User user, long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            Recipe recipe = optRecipe.get();
            Optional<RecipeComment> optRecipeComment = recipeCommentRepository
                    .findRecipeCommentByUserAndRecipe(user, recipe);
            if (optRecipeComment.isEmpty()){
                comment.setUser(user);
                comment.setRecipe(recipe);
                recipeCommentRepository.save(comment);
                log.info("User commented on recipe id=" + recipeId + ": " + comment);
            } else {
                log.warn("User tried to comment on recipe more than once. Comment discarded!");
            }
        } else {
            log.warn("Recipe id=" + recipeId + " not found in database!");
        }
    }

    public List<RecipeCommentDTO> getAllRecipeComments(long recipeId){
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            return optRecipe.get().getRecipeComments().stream().map(RecipeCommentDTO::new).collect(Collectors.toList());
        } else {
            log.warn("Recipe id=" + recipeId + " not found in database!");
            return new ArrayList<>();
        }
    }

    public void updateRecipeComment(RecipeComment updatedComment){
        Optional<RecipeComment> optOldComment = recipeCommentRepository.findById(updatedComment.getId());
        if (optOldComment.isPresent()){
            RecipeComment oldComment = optOldComment.get();
            oldComment.update(updatedComment);
            recipeCommentRepository.save(oldComment);
            log.info("User updated recipe comment: " + oldComment);
        } else {
            log.warn("Cannot update recipe comment id=" + updatedComment.getId() + " - does not exist in database!");
        }

    }

    public void deleteRecipeComment(long id){
        Optional<RecipeComment> optRecipeComment = recipeCommentRepository.findById(id);
        if (optRecipeComment.isPresent()){
            recipeCommentRepository.delete(optRecipeComment.get());
            log.info("User deleted recipe comment: " + optRecipeComment.get());
        } else {
            log.warn("Cannot delete recipe comment id=" + id + " - does not exist in database!");
        }
    }

    public void voteOnRecipeComment(long id, User user, VoteType voteType) {
        Optional<RecipeComment> optRecipeComment = recipeCommentRepository.findById(id);
        if (optRecipeComment.isPresent()) {
            if (optRecipeComment.get().getUser().getId() == user.getId()) {
                log.warn("Cannot vote on recipe id=" + id + " - user tried to vote on his own recipe comment");
                return;
            }
            List<RecipeCommentVote> recipeCommentVotes = optRecipeComment.get().getRecipeCommentVotes();
            for (RecipeCommentVote recipeCommentVote : recipeCommentVotes) {
                if (recipeCommentVote.getUser().getId() == user.getId()) {
                    if (recipeCommentVote.getVoteType() == voteType) {
                        log.warn("Cannot up vote recipe comment id=" + id + " - user tried to cast same vote more than once");
                    } else {
                        optRecipeComment.get().withdrawVote(recipeCommentVote);
                        recipeCommentRepository.save(optRecipeComment.get());
                        log.info("User: " + user.getUsername() + " withdrew vote recipe comment id=" + id);
                    }
                    return;
                }
            }
            optRecipeComment.get().castVote(user, voteType);
            recipeCommentRepository.save(optRecipeComment.get());
            log.info("User: " + user.getUsername() + " cast vote recipe comment id=" + id + " -> " + voteType);
        } else {
            log.warn("Cannot vote on recipe comment id=" + id + "  - does not exist in database!");
        }
    }
}
