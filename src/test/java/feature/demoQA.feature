Feature: Registro de usuario, Completar formulario

  @Test_01
  Scenario: Registrar un usuario
    Given el usuario ingresa a la pagina de DemoQA
    And  va a registrar usuario
    And ingresa los siguientes datos para realizar el registro

      | NombreCampo                 | valor                   |
      | first name                  | claudio30               |
      | last name                   | vera                    |
      | userName                    | claverad30              |
      | password                    | Ts@ft2021               |

    Then validar mensaje de registro del usuario

  @Test_02
  Scenario: Completar formulario con datos desde bbdd y dataTable
    Given el usuario ingresa a la pagina de DemoQA
    And  se dirige a la pagina de formulario de practica
    And ingresa los campos desde la tabla persona
    And ingresa las siguientes materias

      | Materia              |
      | Maths                |
      | Computer Science     |
      | Chemistry            |

    And selecciona los siguientes hobbies

      | Hobbies               |
      | Sports                |
      | Reading               |

    And ingresar direccion

  @Test_03
  Scenario: Completar formulario con datos desde bbdd y dataTable
    Given el usuario ingresa a la pagina de DemoQA
    And  se dirige a la pagina BookStore
    And ingresar al login con datos de la bbdd
    And seleccionar los siguientes libros

      | Libros                                            |
      | Git Pocket Guide                                  |
      | Learning JavaScript Design Patterns               |
      | Designing Evolvable Web APIs with ASP.NET         |
      | Speaking JavaScript                               |
      | Programming JavaScript Applications               |

    And ir a profile y obtener evidencia de los libros seleccionados


