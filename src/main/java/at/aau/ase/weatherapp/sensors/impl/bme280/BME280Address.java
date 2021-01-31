package at.aau.ase.weatherapp.sensors.impl.bme280;

public enum BME280Address {
    I2C_0x76((byte)0x76),
    I2C_0x77((byte)0x77);

    private final byte value;

    BME280Address(final byte newValue) {
        value = newValue;
    }

    public byte getValue() { return value; }
}
