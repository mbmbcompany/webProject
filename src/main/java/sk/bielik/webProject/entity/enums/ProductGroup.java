package sk.bielik.webProject.entity.enums;

public enum ProductGroup {

    ELECTRO('E'),
    GROCERY('G'),
    FURNITURE('F');

    private char kode;

    ProductGroup(char kode) {
        this.kode=kode;
    }

    public static ProductGroup getEnumFromCharacter(Character character) {
        if (character.equals('E')){
            return ProductGroup.ELECTRO;
        }
        if (character.equals('G')){
            return ProductGroup.GROCERY;
        }
        if (character.equals('F')){
            return ProductGroup.FURNITURE;
        }
        else throw new NullPointerException();
    }

    public char getKode() {
        return kode;
    }

    public void setKode(char kode) {
        this.kode = kode;
    }
}
