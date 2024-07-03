package btreeplus;

public class Test2 {
    public static void main(String[] args) {
    	BPlusTree bPlusTree = new BPlusTree(3);

        // Insertar claves con datos asociados en el árbol B+
        /*bPlusTree.insert("Juan Pérez", 12345678);
        bPlusTree.insert("María Gómez", 87654321);
        bPlusTree.insert("Carlos López", 98765432);
        bPlusTree.levelOrderTraversal();

        // Probar la búsqueda de nombres de pacientes y obtener su DNI asociado
        System.out.println("Buscar paciente 'Juan Pérez': " + bPlusTree.search("Juan Pérez")); // Debería imprimir '12345678'
        System.out.println("Buscar paciente 'Pedro Ramírez': " + bPlusTree.search("Pedro Ramírez")); // Debería imprimir '-1'

        // Eliminar claves del árbol B+
        bPlusTree.remove("Juan Pérez");
        System.out.println("Buscar paciente 'Juan Pérez' después de eliminar: " + bPlusTree.search("Juan Pérez")); // Debería imprimir '-1'
    	*/
    	bPlusTree.insert("A", 0);
    	bPlusTree.insert("B", 0);
    	bPlusTree.insert("C", 0);
    	bPlusTree.insert("D", 0);
    	bPlusTree.insert("E", 0);
    	bPlusTree.insert("F", 0);
    	bPlusTree.levelOrderTraversal();
    }
}