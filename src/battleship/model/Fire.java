package battleship.model;

import battleship.exceptions.NotInsideTheBoardException;
import battleship.utils.Converter;
import battleship.utils.Validators;

class Fire {

    static int[] shot(String fireShot) throws NotInsideTheBoardException {
        int[] indexes = Converter.fireConvert(fireShot);
        Validators.fireValidate(indexes);

        return indexes;
    }
}
