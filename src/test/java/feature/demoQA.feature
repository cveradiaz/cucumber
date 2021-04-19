Feature: Registro de usuario, Completar formulario

  @Test_01
  Scenario: Registrar un usuario
    Given el usuario ingresa a la pagina de DemoQA
    And  va a registrar usuario
    And ingresa los siguientes datos para realizar el registro

      | NombreCampo                 | valor                   |
      | first name                  | clau2                   |
      | last name                   | vera                    |
      | userName                    | claverad                |
      | password                    | Ts@ft2022               |

    Then validar mensaje de registro del usuario

  @Test_02
  Scenario: LLenar formulario
    Given el usuario ingresa a la pagina de DemoQA
    And  va a llenar formulario
    And ingresa los siguientes datos para completar formulario

      | NombreCampo                 | valor                   |
      | first name                  | clau                    |
      | last name                   | vera                    |
      | userName                    | claverad                |
      | password                    | Ts@ft2022               |

