package model;

public class ApplyItem implements IAction {
    private IItem item;
    private IPokemon target;

    public ApplyItem(IItem item, IPokemon target) 
    {
        this.item = item;
        this.target = target;
    }

    public void perform() {
        this.item.use(this.target);
    }

    public String toString() {
        return String.format("Utiliser %s", this.item, this.target);
    }
}
