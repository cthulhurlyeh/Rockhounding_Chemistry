package com.globbypotato.rockhounding_chemistry.integration.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_chemistry.compat.jei.chemicalextractor.ExtractorRecipeWrapper;
import com.globbypotato.rockhounding_chemistry.handlers.ModRecipes;
import com.globbypotato.rockhounding_chemistry.machines.recipe.ChemicalExtractorRecipe;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_chemistry.ChemicalExtractor")
public class ExtractorRecipes {
	private static String name = "Chemical Extractor Recipe";

    @ZenMethod
    public static void add(String category, IItemStack input, String[] ingredient, int[] quantity) {
        if(ingredient == null || quantity == null || ingredient.length != quantity.length) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}

        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<Integer> quantities = new ArrayList<Integer>();

        for(int x = 0; x < ingredient.length; x++){
        	ingredients.add(ingredient[x]);
        	quantities.add(quantity[x]);
        }

        MineTweakerAPI.apply(new AddToExtractor(new ChemicalExtractorRecipe(category, CTSupport.toStack(input), ingredients, quantities)));
    }
		    private static class AddToExtractor implements IUndoableAction {
		    	private ChemicalExtractorRecipe recipe;
		    	public AddToExtractor(ChemicalExtractorRecipe recipe){
		          super();
		          this.recipe = recipe;
		    	}
		    	@Override
		    	public void apply() {
		    		ModRecipes.extractorRecipes.add(this.recipe);
		    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new ExtractorRecipeWrapper(this.recipe));
		    	}
		    	@Override
		    	public void undo() {
		    		for(ChemicalExtractorRecipe recipe : ModRecipes.extractorRecipes){
		    			if(recipe.equals(this.recipe)){
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new ExtractorRecipeWrapper(recipe));
		    				ModRecipes.extractorRecipes.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public boolean canUndo() {return true;}
		    	@Override
		    	public String describe() {return null;}
		    	@Override
		    	public String describeUndo() {return null;}
		    	@Override
		    	public Object getOverrideKey() {return null;}
		    }


    @ZenMethod
    public static void remove(IItemStack input) {
        if(input == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        MineTweakerAPI.apply(new RemoveFromExtractor(CTSupport.toStack(input)));    
    }
		    private static class RemoveFromExtractor implements IUndoableAction {
		    	private ItemStack input;
		    	private ChemicalExtractorRecipe undoRecipe;
		    	public RemoveFromExtractor(ItemStack input) {
		    		super();
		    		this.input = input;
		    	}
		    	@Override
		    	public void apply() {
		    		for(ChemicalExtractorRecipe recipe : ModRecipes.extractorRecipes){
		    			if(this.input != null && recipe.getInput().isItemEqual(this.input)){
				    		this.undoRecipe = recipe;
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new ExtractorRecipeWrapper(recipe));
		    				ModRecipes.extractorRecipes.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public void undo() {
		    		if(this.undoRecipe != null){
			    		ModRecipes.extractorRecipes.add(this.undoRecipe);
			    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new ExtractorRecipeWrapper(this.undoRecipe));
		    		}
		    	}
		    	@Override
		    	public boolean canUndo() {return true;}
		    	@Override
		    	public String describe() {return null;}
		    	@Override
		    	public String describeUndo() {return null;}
		    	@Override
		    	public Object getOverrideKey() {return null;}
		    }
		    
    @ZenMethod
    public static void inhibit(String[] input) {
        if(input == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        ArrayList<String> inputs = new ArrayList<String>();
        if(input.length > 0){
	        for(int x = 0; x < input.length; x++){
	        	inputs.add(input[x]);
	        }
        }
        MineTweakerAPI.apply(new InhibitFromExtractor(inputs));    
    }
		    private static class InhibitFromExtractor implements IUndoableAction {
		    	ArrayList<String> inputs = new ArrayList<String>();
		    	public InhibitFromExtractor(ArrayList<String> input) {
		    		super();
	    			this.inputs.addAll(input);
		    	}
		    	@Override
		    	public void apply() {
		    		for(int x = 0; x < this.inputs.size(); x++){
		    			if(!ModRecipes.inhibitedElements.contains(this.inputs.get(x))){
		    				ModRecipes.inhibitedElements.add(this.inputs.get(x));
		    			}
		    		}
		    	}
		    	@Override
		    	public void undo() {
		    		if(this.inputs.size() > 0 && ModRecipes.inhibitedElements.size() > 0){
		    			for(int x = 0; x < ModRecipes.inhibitedElements.size(); x++){
				    		for(int y = 0; y < this.inputs.size(); y++){
				    			if(ModRecipes.inhibitedElements.get(x).matches(this.inputs.get(y))){
				    				ModRecipes.inhibitedElements.remove(x);
				    			}
				    		}
			    		}
		    		}
		    	}
		    	@Override
		    	public boolean canUndo() {return true;}
		    	@Override
		    	public String describe() {return null;}
		    	@Override
		    	public String describeUndo() {return null;}
		    	@Override
		    	public Object getOverrideKey() {return null;}
		    }

}