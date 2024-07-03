package btreeplus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BPlusTree {
    public class Entry {
        String key; // Clave para el nombre del paciente
        ArrayList<Integer> values; // Lista de DNIs del paciente

        public Entry(String key, int value) {
            this.key = key;
            this.values = new ArrayList<>();
            this.values.add(value);
        }

        public void addValue(int value) {
            this.values.add(value);
        }
    }

    private class NodeBPT {
        ArrayList<Entry> entries;
        ArrayList<NodeBPT> children;
        boolean isLeaf;

        public NodeBPT(boolean isLeaf) {
            this.entries = new ArrayList<>();
            this.children = new ArrayList<>();
            this.isLeaf = isLeaf;
        }
    }

    private NodeBPT root;
    private int maxKeys;

    public BPlusTree(int maxKeys) {
        this.root = new NodeBPT(true);
        this.maxKeys = maxKeys - 1; // Establecer el máximo de claves por nodo
    }

    public void insert(String key, int value) {
        NodeBPT r = root;
        if (r.entries.size() == maxKeys) {
            NodeBPT s = new NodeBPT(false);
            s.children.add(r);
            splitChild(s, 0, r);
            root = s;
        }
        insertNonFull(root, key, value);
    }

    private void splitChild(NodeBPT parent, int i, NodeBPT child) {
        NodeBPT sibling = new NodeBPT(child.isLeaf);
        int mid = maxKeys / 2;
        parent.entries.add(i, child.entries.get(mid));
        sibling.entries.addAll(child.entries.subList(mid + 1, child.entries.size()));

        if (!child.isLeaf) {
            sibling.children.addAll(child.children.subList(mid + 1, child.children.size()));
            child.children.subList(mid + 1, child.children.size()).clear();
        }

        child.entries.subList(mid, child.entries.size()).clear();
        parent.children.add(i + 1, sibling);
    }

    private void insertNonFull(NodeBPT node, String key, int value) {
        int i = 0;
        while (i < node.entries.size() && key.compareTo(node.entries.get(i).key) > 0) {
            i++;
        }
        if (i < node.entries.size() && key.equals(node.entries.get(i).key)) {
            node.entries.get(i).addValue(value);
        } else if (node.isLeaf) {
            node.entries.add(i, new Entry(key, value));
        } else {
            NodeBPT child = node.children.get(i);
            if (child.entries.size() == maxKeys) {
                splitChild(node, i, child);
                if (key.compareTo(node.entries.get(i).key) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key, value);
        }
    }

    public ArrayList<Integer> search(String key) {
        return searchAllOccurrences(root, key);
    }

    private ArrayList<Integer> searchAllOccurrences(NodeBPT node, String key) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < node.entries.size() && key.compareTo(node.entries.get(i).key) > 0) {
            i++;
        }
        while (i < node.entries.size() && key.equals(node.entries.get(i).key)) {
            result.addAll(node.entries.get(i).values);
            i++;
        }
        if (node.isLeaf) {
            return result;
        } else {
            if (i < node.entries.size() && key.equals(node.entries.get(i).key)) {
                result.addAll(searchAllOccurrences(node.children.get(i), key));
            }
            if (i < node.children.size()) {
                result.addAll(searchAllOccurrences(node.children.get(i), key));
            }
            return result;
        }
    }

    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<NodeBPT> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int nodesAtCurrentLevel = queue.size();

            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                NodeBPT node = queue.poll();
                System.out.print("[");
                for (Entry entry : node.entries) {
                    System.out.print("(" + entry.key + ", " + entry.values + ")");
                }
                System.out.print("] ");

                if (!node.isLeaf) {
                    for (NodeBPT child : node.children) {
                        queue.offer(child);
                    }
                }
            }
            System.out.println();
        }
    }
}
