package de.ljfa.elofharmony.items;


public class ModItems {

    public static ItemElement elementOfHarmony;
    public static ItemTwilicane twilicane;
    public static ItemResource resource;
    
    public static void init() {
        elementOfHarmony = new ItemElement();
        twilicane = new ItemTwilicane();
        resource = new ItemResource();
    }
}
