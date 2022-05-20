package business.entities;

import java.util.ArrayList;

/**
 * Clase que se encargará de manejar toda la lógica referente al jugador y sus movimientos.
 */
public class Human extends Player {

    /**
     * Constructor de Human.
     * @param board Board donde están colocadas los barcos del jugador.
     */
    public Human(Board board) {
        super(board);
    }

    @Override
    public void attack(Player player, int x, int y) {
        player.getBoard().sendAttack(x, y);
    }

}
