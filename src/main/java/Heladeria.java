import java.util.Scanner;

public class Heladeria {

public static class Nodo{
    public Nodo padre;
    public Nodo izquierda;
    public Nodo derecha;
    public Object dato;
    public int ind;

    public Nodo(int indice){
        this.padre = null;
        this.izquierda = null;
        this.derecha = null;
        this.dato = null;
        ind = indice;
    }
}

public static class Arbol{
    public Nodo raiz;

    public Arbol(){
        this.raiz = null;
    }

    public void insertar(int x, Object sabor){
        Nodo nuevo = new Nodo(x);
        nuevo.dato = sabor;
        if(raiz == null){
            raiz = nuevo;
        } else{
            Nodo aux = raiz;
            while(aux != null){
                nuevo.padre = aux;
                if(nuevo.ind>= aux.ind){
                    aux = aux.derecha;
                } else{
                    aux = aux.izquierda;
                }
            }
            if(nuevo.ind<nuevo.padre.ind){
                nuevo.padre.izquierda = nuevo;
            } else{
                nuevo.padre.derecha = nuevo;
            }
        }
    }

    public void recorrer(Nodo nuevo) {
        if (nuevo != null) {
            recorrer(nuevo.izquierda);
            System.out.println("Sabores: " + nuevo.ind + " - " + nuevo.dato);
            recorrer(nuevo.derecha);
        }
    }

    public Nodo busqueda(Nodo nodo, int x){
        if(nodo == null){
            return null;
        }
        if(nodo.ind == x){
            return nodo;
        }
        if(x < nodo.ind){
            return busqueda(nodo.izquierda, x);
        } else{
            return busqueda(nodo.derecha, x);
        }
    }

    public Nodo nPadre(Nodo nodo){
        Nodo temp = nodo.derecha;
        while(temp.izquierda != null){
            temp = temp.izquierda;
        }
        return temp;
    }

    public void eliminacion(int x){
        Nodo temp = busqueda(raiz, x);
        if (temp == null) {
            return;
        }
        if (temp.izquierda == null && temp.derecha == null) {
            if (temp.padre == null) {
                raiz = null;
            } else if (temp.padre.izquierda == temp) {
                temp.padre.izquierda = null;
            } else {
                temp.padre.derecha = null;
            }
        } else if (temp.izquierda == null || temp.derecha == null) {

            // el operador condicional "?" nos sirve para asignar un nodo hijo (izq o der) al Nodo hijo.
            Nodo hijo = (temp.izquierda != null) ? temp.izquierda : temp.derecha;
            hijo.padre = temp.padre;
            if (temp.padre == null) {
                raiz = hijo;
            } else if (temp.padre.izquierda == temp) {
                temp.padre.izquierda = hijo;
            } else {
                temp.padre.derecha = hijo;
            }
        } else {
            Nodo nPadre = nPadre(temp);
            temp.ind = nPadre.ind;
            temp.dato = nPadre.dato;
            eliminacion(nPadre.ind);
        }
    }
}

    public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    Arbol arbol = new Arbol();
    int ind, x=0, z, y, in = 0, in2, in3=0, in4=0, ind2=0;
    Object sabor;

    while(in < 5) {
        System.out.println("------");
        System.out.println("MENÚ");
            System.out.println("1.- INSERTAR SABOR");
            System.out.println("2.- MOSTRAR SABORES");
            System.out.println("3.- ELIMINAR SABOR");
            System.out.println("4.- BUSCAR SABOR");
            System.out.println("PRESIONE CUALQUIER OTRO NÚMERO PARA SALIR");
            System.out.println("------");
            in = entrada.nextInt();

            switch (in) {
                case 1:
                    System.out.println("SABORES A INSERTAR: ");
                    in2 = entrada.nextInt();
                    do {
                        System.out.println("SABOR: ");
                        sabor = entrada.next();
                        x++;
                        arbol.insertar(x, sabor);
                    } while (x < in2);
                    break;

                    case 2:
                    arbol.recorrer(arbol.raiz);
                    break;

                    case 3:
                    System.out.println("INGRESE EL NÚMERO DEL SABOR A ELIMINAR: ");
                    z = entrada.nextInt();
                    arbol.eliminacion(z);
                    if(arbol.raiz == null){
                        x = 0;
                    }
                    break;

                    case 4:
                    System.out.println("SELECCIONE EL NÚMERO DEL SABOR QUE BUSCA: ");
                    y = entrada.nextInt();
                    arbol.busqueda(arbol.raiz, y);
                    break;

                default:

            }
        }
    }

}
