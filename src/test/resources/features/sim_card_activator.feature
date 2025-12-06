Feature: Is the sim card active?
  Checks if the entered sim card iccid is active or not.

  Scenario: Check active sim card
    Given iccid = "1255789453849037777"
    When activate
    Then success = "true"

  Scenario: Check inactive sim card
    Given iccid = "8944500102198304826"
    When activate
    Then success = "false"