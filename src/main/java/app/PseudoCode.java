package app;

public class PseudoCode {

    /*
    PSEUDOCODE:
    - Loop thought Array of terrain heights (first-round)
        (Analise behaviour: goes down, same or up)
        If terrain goes down (find first_edge):
            traverse forward and look for the last_edge (find same height OR larger):
                if last_edge is found:
                    count water blocks between then
                    run again (find first edge) from index after the last edge (recursive approach)
                if another last edge is NOT found
                    run (find first edge) from index after the first edge (recursive approach)
     - After end of first-round, invert the Array and run second-round
         If terrain goes down (find first_edge):
             traverse forward and look for the last_edge (find larger height)
                check if these indexes are already included (they might be submerged and already included in first-round)
        Returns final sum of water blocks data
     */
}
