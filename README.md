# Distance4J
Distance4J is a lightweight Java library that provides an easy way to convert and apply arithmetic operations on distance measures.

The main purpose of Distance4J is to prevent common errors and annoying boilerplate when manipulating distances in different length units. With Distance4J, the provider and the consumer of a distance are unit-agnostic. Each client can choose the length unit to use, without needing to adapt to the unit in which the distance is provided with cunbersome conversions.

## How to use Distance4J
Instead of modelising a distance with a primitive magnitude with some implicit unit that is documented somewhere in the project, Distance4J provides a unit-agnostic model.

A distance can be created with any of the available factory methods:

```java
Distance yards = Distance.of(12, LengthUnits.ImperialUS.YARD);
Distance metres = Distance.ofMetres(10.15);
```

Once a distance is instantiated, the client can manipulate it without needing any knowledge on the unit that the provider used for the initial measure. The client can manipulate the distance using the units it needs.

```java
Distance addition = myDistance.plus(50, LengthUnits.SI.DECIMETRE);
Distance subtraction = myDistance.minus(anotherDistance);
```

Finally, when the client is done with the distance manipulation, the holded value can be retrieved in any unit:

```java
double feet = myDistance.to(LengthUnits.ImperialUS.FOOT);
double metres = myDistance.toMetres();
```

## Available length units:
The length units are classified by system of meausurements.

### International System of Units (SI)
* MILLIMETRE
* CENTIMETRE
* DECIMETRE
* METRE
* DECAMETRE
* HECTOMETRE
* KILOMETRE

### Imperial / United States customary
* THOU
* INCH
* FOOT
* YARD
* CHAIN
* FURLONG
* MILE
* LEAGUE

### Creating a custom length unit
Creating a custom unit is as simple as implementeing the `LengthUnit` interface. It is a functional interface that provides an equivalence of the unit in metres.

For example, creating a custom length unit that has an equivalence of 2 metres is as simple as this:

```java
public class MyCustomUnit implements LengthUnit {
    
    @Override
    public BigDecimal toMetres() {
        return BigDecimal.valueOf(2);
    }

}
```
