package com.codecool.codecoolshopspring.model.recipes;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import com.codecool.codecoolshopspring.model.recipes.cookingphase.CookingPhase;
import com.codecool.codecoolshopspring.model.recipes.mealcomponent.MealComponent;
import com.codecool.codecoolshopspring.model.votes.RecipeVote;
import com.codecool.codecoolshopspring.model.votes.VoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @NotNull
    private String title;

    @NotNull
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeVote> recipeVotes = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealComponent> mealComponents = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CookingPhase> cookingPhases = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeComment> recipeComments = new ArrayList<>();

    private byte[] image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public void setMealComponentsRelation(){
        mealComponents.forEach(component -> component.setRecipe(this));
    }

    public void setCookingPhasesRelation(){
        cookingPhases.forEach(phase -> phase.setRecipe(this));
    }

    private void updateMealComponents(List<MealComponent> newMealComponents){
        mealComponents.clear();
        for (MealComponent mealComponent : newMealComponents) {
            mealComponents.add(mealComponent);
            mealComponent.setRecipe(this);
        }
    }

    private void updateCookingPhases(List<CookingPhase> newCookingPhases){
        cookingPhases.clear();
        for (CookingPhase cookingPhase : newCookingPhases) {
            cookingPhases.add(cookingPhase);
            cookingPhase.setRecipe(this);
        }
    }

    public void update(Recipe recipe){
        this.title = recipe.getTitle();
        updateMealComponents(recipe.getMealComponents());
        updateCookingPhases(recipe.getCookingPhases());
        this.image = recipe.getImage();
    }

    public void withdrawVote(RecipeVote recipeVote){
        recipeVotes.remove(recipeVote);
    }

    public void castVote(User user, VoteType voteType){
        recipeVotes.add(RecipeVote.builder()
                .voteType(voteType)
                .user(user)
                .recipe(this)
                .build());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", title='" + title + '\'' +
                ", mealComponents=" + mealComponents +
                ", cookingPhases=" + cookingPhases +
                ", recipeComments=" + recipeComments +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
