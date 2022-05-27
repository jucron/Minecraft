package main.java;

public class PseudoCode {

    /*
    PSEUDOCODE:
    - Loop thought Array of terrain heights
        (Analise behaviour: goes down, same or up)
        If terrain goes down (find first edge):
            traverse forward and look for the final edge (find last edge):
                if last edge is found:
                    count water blocks between then
                    run (find first edge) from index after the last edge (recursive approach)
                if another last edge is NOT found
                    run (find first edge) from index after the first edge (recursive approach)
     */

}
