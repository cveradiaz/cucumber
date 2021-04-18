Feature: Registro de usuario

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

    Then la cotizacion debe mostrar 8 productos  en el carro de compras
    And mostrar el detalle y valor total a pagar