/**
 * Created by julian on 29/09/2016.
 */
public class parametros_lista {
    private int imagen;
    private String nombre,apellido,cosa;

    public parametros_lista(int imagen, String nombre, String apellido, String cosa) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cosa = cosa;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCosa() {
        return cosa;
    }

    public void setCosa(String cosa) {
        this.cosa = cosa;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
