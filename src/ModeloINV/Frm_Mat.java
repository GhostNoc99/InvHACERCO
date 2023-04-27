package ModeloINV;

public class Frm_Mat {

    private int id, cant, material;
    private String marca, articulo, anot, crea;

    public Frm_Mat() {
    }

    //ADD
    public Frm_Mat(int cant, String marca, String articulo, String anot) {
        this.cant = cant;
        this.marca = marca;
        this.articulo = articulo;
        this.anot = anot;
    }

    //UPDATE
    public Frm_Mat(int id, int cant, String marca, String articulo, String anot) {
        this.id = id;
        this.cant = cant;
        this.marca = marca;
        this.articulo = articulo;
        this.anot = anot;
    }
    
    //GETTER & SELLER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getAnot() {
        return anot;
    }

    public void setAnot(String anot) {
        this.anot = anot;
    }

    public String getCrea() {
        return crea;
    }

    public void setCrea(String crea) {
        this.crea = crea;
    }
    
    

}
