class RecycledLoader extends Loader {

    RecycledLoader(int identifier, Cruise cruise) {
        super(identifier, cruise.extendDuration());
    }

    @Override
    RecycledLoader serve(Cruise cruise) {
        if (canServe(cruise)) {
            return new RecycledLoader(this.getIdentifier(), cruise);
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }
}
