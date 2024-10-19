# MobileAutomationFramework

## Overview
This repository contains a mobile automation testing framework built using Java, designed for scalable and maintainable testing of mobile applications.

## Features
- Supports Android and iOS platforms
- Built with Appium
- Implements the Page Object Model (POM) design pattern
- Extensible structure for adding test cases

## Prerequisites
- **Java 11+**
- **Appium Server** installed and running
- **Maven**
- Android SDK or iOS Xcode

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/YzeedShahin/MobileAutomationFramework.git

2. Install dependencies:
   ```bash
   mvn clean install

4. Configure your mobile device/emulator and Appium server:
   * Ensure your device is connected or the emulator is running.
   * Start the Appium server.
    Update `config.properties` with the necessary details like device name, platform, etc.

## Running Tests
- To execute all tests, run:
  ```bash
  mvn test
