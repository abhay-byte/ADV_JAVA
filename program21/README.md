# Program 21 - Java RMI Calculator

## Description
Implements a simple RMI-based Calculator.

## Files
- `Calculator.java`: Remote interface
- `CalculatorImpl.java`: Implementation
- `CalculatorClient.java`: Client code

## Instructions

### Step 1: Compile All Files
```cmd
javac Calculator.java CalculatorImpl.java CalculatorClient.java
```

### Step 2: Generate Stub (if using Java 8 or earlier)
```cmd
rmic CalculatorImpl
```

### Step 3: Start RMI Registry
```cmd
start rmiregistry
```

### Step 4: Start Server
```cmd
java CalculatorImpl
```

### Step 5: Run Client
```cmd
java CalculatorClient
```

Make sure all `.class` files are in the same directory, and RMI registry is running in that folder.