Feature: Cotizacion
  Como usuario quiero realizar una cotizacion
  de cada componente de un computador

  @cotizacion
  Scenario: Cotización de productos
    Given el usuario ingresa a la pagina de PCFactory
    And  busca y selecciona productos

      | TipoProducto                 | nombreProducto                                          |
      | placa madre intel asus       | M/B Z390 TUF Plus Gaming WiFi (1151-v2)                 |
      | procesador                   | CPU Core i5-9600K 3.70 GHz 9MB (1151-v2)                |
      | memoria ram ddr4             | Memoria Notebook 16GB SoDimm DDR4 3200MHz Value RAM     |
      | tarjeta de video             | Video NVIDIA GTX1650 O4G TUF Gaming                     |
      | torre                        | Gabinete ATX Athena M2 RGB                              |
      | tarjeta de sonido            | Tarjeta Sonido Audigy RX PCIe 24 Bits                   |
      | teclado mecanico             | Teclado Gamer Volt Mecánico USB Inglés Negro            |
      | monitor gamer                | Monitor Gamer 27" G271 1ms 144 Hz FreeSync              |

    Then la cotizacion debe mostrar 8 productos  en el carro de compras
    And mostrar el detalle y valor total a pagar