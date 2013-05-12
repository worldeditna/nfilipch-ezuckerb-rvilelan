package model;

public class UseSkill implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public UseSkill(IPokemon thedealer, IPokemon target) {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform()
    {
    	
        this.thedealer.useSkill(this.target);
    }

    public String toString() {
        return String.format("Utiliser la super-attaque");
    }
}