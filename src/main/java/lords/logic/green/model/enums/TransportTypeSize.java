package lords.logic.green.model.enums;

import lombok.Getter;

@Getter
public enum TransportTypeSize {
    SMALL(0.8),
    MID(1.0),
    LARGE(1.5);

    private final double sizeMultiplier;

    TransportTypeSize(double sizeMultiplier) {
        this.sizeMultiplier = sizeMultiplier;
    }

}
