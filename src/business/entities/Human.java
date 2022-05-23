package business.entities;

import business.GameManager;

/**
 * Clase que se encargará de manejar toda la lógica referente al jugador y sus movimientos.
 */
public class Human extends Player {

    GameManager gameManager;

    /**
     * Constructor de Human.
     * @param board Board donde están colocadas los barcos del jugador.
     */
    public Human(Board board, GameManager gameManager) {
        super(board);
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                if (isRecharging()) {
                    Thread.sleep(1000);
                    setRecharging(false);
                    gameManager.updatePhase("Attack");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
