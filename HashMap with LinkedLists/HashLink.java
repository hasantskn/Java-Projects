/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hasan
 */
public class HashLink {
    HashLinkNode[] hashTable;
    int numOfCol = 0;

        // stores the size of HashTable
        int size;

        // Constructor
        HashLink(int hashTableSize)
        {
            // Creating an empty Hash Table
            hashTable = new HashLinkNode[hashTableSize];
            size = 0;
        }
       

        // Function to insert a value/element
        public void insert(String value)
        {
            size++;

            // gets the position/index where the value should be
            // stored
            int position = hash(value);

            // creates a node for storing value
            HashLinkNode node = new HashLinkNode(value);

            HashLinkNode start = hashTable[position];

            if (hashTable[position] == null)
                hashTable[position] = node;
            else {
                node.next = start;
                start.prev = node;
                hashTable[position] = node;
                if(!start.data.equals(node.data)){
                    numOfCol+=1;
                }
            }
        }

        // Definition of Hash function
        public int hash(String x)
        {
            int hashValue = x.hashCode();

            hashValue %= hashTable.length;

            if (hashValue < 0)
                hashValue += hashTable.length;

            return hashValue;
        }

}
