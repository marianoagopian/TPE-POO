package backend;

import backend.model.Figure;

public enum Operations {

    DRAW("Dibujar %s"){
        public void operate(Figure figure) {

        }
    },ERASE("Borrar %s"){
        public void operate(Figure figure) {

        }
    },CHANGE_BORDER_COLOR("Cambiar color de borde de %s"){
        public void operate(Figure figure) {

        }
    },CHANGE_FILL_COLOR("Cambiar color de relleno de %s"){
        public void operate(Figure figure) {

        }
    },ENLARGE("Agrandar %s"){
        public void operate(Figure figure) {

        }
    },REDUCE("Achicar %s"){
        public void operate(Figure figure) {

        }
    };

    private final String description;

    Operations(String description) {
        this.description = description;
    }


}
