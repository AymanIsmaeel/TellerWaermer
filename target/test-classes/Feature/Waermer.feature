

Feature: Waermer

  Scenario: Wärmegerät einschalten
    Given das Wärmegerät ist ausgeschaltet
    When ich das Wärmegerät einschalte
    Then ist das Wärmegerät betriebsbereit
    And der Tellerstapel ist leer

  Scenario: Leeres Wärmegerät ausschalten
    Given das Wärmegerät ist eingeschaltet
    And das Wärmegerät ist leer
    When ich das Wärmegerät ausschalte
    Then ist das Wärmegerät ausser Betrieb
    And es können keine Teller hinzugefügt werden

  Scenario: Gefülltes Wärmegerät ausschalten
    Given das Wärmegerät ist eingeschaltet
    And das Wärmegerät ist gefüllt
    When ich das Wärmegerät ausschalte
    Then erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist
    And das Wärmegerät bleibt in Betrieb

  Scenario: Volles Wärmegerät ausschalten
    Given das Wärmegerät ist eingeschaltet
    And das Wärmegerät ist voll
    When ich das Wärmegerät ausschalte
    Then erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist
    And das Wärmegerät bleibt in Betrieb



  Scenario Outline: Teller hinzufügen/entnehmen
    Given das Wärmegerät ist eingeschaltet
    And die maximale Kapazität des Tellerstapels ist 10
    And im Wärmegerät sind <Telleranzahl> Teller
    When ich einen Teller <Maßnahme>
    Then sind <NeueTelleranzahl> Teller im Wärmegerät
    And eine <Hinweismeldung> informiert mich ob das <Maßnahme> erfolgreich war
    Examples:
      |Telleranzahl | Maßnahme   |NeueTelleranzahl   | Hinweismeldung                                               |
      |0            | Hinzufügen |  1                | Ein Teller hinzugefügt                                       |
      |8            | Hinzufügen |  9                | Ein Teller hinzugefügt                                       |
      |9            | Hinzufügen |  10               | Ein Teller hinzugefügt                                       |
      |10           | Hinzufügen |  10               | Das Wärmegerät ist voll! Sie können keine Teller hinzufügen! |
      |0            | Entnehmen  |  0                | Das Wärmegerät ist leer! Sie können keine Teller entnehmen!  |
      |1            | Entnehmen  |  0                | Ein Teller entnommen                                         |
      |2            | Entnehmen  |  1                | Ein Teller entnommen                                         |
      |10           | Entnehmen  |  9                | Ein Teller entnommen                                         |


  Scenario Outline: Sichtprüfung
    Given das Wärmegerät ist eingeschaltet
    And die maximale Kapazität des Tellerstapels ist 10
    And im Wärmegerät sind <Telleranzahl> Teller
    When ich einen Teller Ansehen
    Then eine <Hinweismeldung> informiert mich ob das Ansehen erfolgreich war
    Examples:
      |Telleranzahl  |Hinweismeldung |
      |0             |Das Wärmegerät ist leer! Sie können keine Teller ansehen! |
      |1             |Ansehen ist möglich |
      |10            |Ansehen ist möglich |