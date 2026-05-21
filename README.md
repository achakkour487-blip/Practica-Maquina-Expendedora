# Proyecto Máquina Expendedora - Testing y CI/CD

Este repositorio contiene la implementación, las pruebas (Unitarias y E2E) y la integración continua (Pipeline en GitHub Actions) para el proyecto de la Máquina Expendedora en JavaFX.

---

## Fase 1: Fundamentos y Diseño (Justificación Técnica)

### 1. El Testing E2E vs. Testing Unitario
El **Testing End-to-End (E2E)** es crítico porque valida el flujo completo de la aplicación desde la perspectiva del usuario real, asegurando que todos los componentes (interfaz, lógica y configuración) funcionen juntos correctamente. En este proyecto, permite verificar que un usuario puede interactuar con la máquina expendedora (insertar monedas, ver su saldo y comprar un producto) sin errores visuales o de navegación.

Sin embargo, es más costoso que el unitario por:
* **Complejidad de ejecución**: Requiere levantar el entorno gráfico completo (JavaFX).
* **Tiempo**: Las pruebas E2E son más lentas al simular interacciones humanas como clics, escritura y cierres de ventanas.
* **Fragilidad**: Pequeños cambios en la interfaz pueden romper los tests E2E, mientras que los unitarios son mucho más estables, rápidos y aislados.

### 2. Continuous Delivery vs. Continuous Deployment
* **Continuous Delivery (Entrega Continua)**: El código se prueba y construye automáticamente, quedando listo para ser desplegado en cualquier momento. Sin embargo, el paso final a producción requiere una decisión o intervención manual.
* **Continuous Deployment (Despliegue Continuo)**: Todo el proceso está totalmente automatizado. Si el código pasa todas las pruebas en el pipeline de CI/CD, se despliega en producción de forma automática sin ninguna intervención humana.

---

## Fase 2: Desarrollo y Testing

Para asegurar la máxima calidad del software, se han implementado pruebas exhaustivas superando ampliamente el 50% de cobertura requerido.

* **Pruebas Unitarias (Lógica de negocio):** Se ha evaluado la clase `VendingLogic` probando casos límite (saldos exactos, faltas de 1 céntimo, stock agotado y cálculos de cambio con decimales).
* **Pruebas E2E (TestFX):** Se ha programado un "robot" que navega por la interfaz gráfica probando individualmente cada botón de producto, todas las combinaciones de monedas y los flujos de "Cancelar" y "Comprar".
* **Resultados de Cobertura (JaCoCo):** Este proyecto ha alcanzado un impresionante **92% de cobertura de código (Instrucciones) y un 82% en Ramas**, demostrando una gran robustez frente a errores. *(Se adjuntan capturas y PDF en la entrega).*

---

## Fase 3: Automatización con GitHub Actions

Se ha creado un archivo `.github/workflows/main.yml` que ejecuta un Pipeline completo de Integración Continua cada vez que se sube código al repositorio. Este pipeline automatiza:
1. **Job Build:** Instalación de dependencias de Maven y compilación del proyecto.
2. **Job Test:** Ejecución de las pruebas unitarias de la lógica.
3. **Job E2E:** Ejecución de las pruebas de interfaz gráfica en un entorno "headless" (servidor en la nube de GitHub).

### Sobre la fase de "Deploy"
El pipeline implementado cumple con éxito las 4 fases solicitadas: Build, Test, E2E y Deploy.

Dado que este proyecto es una aplicación nativa de **Escritorio en JavaFX**, plataformas como Vercel o Firebase (exclusivas para entorno Web) no son aplicables. Por ello, el Job de `deploy` se ha configurado utilizando el estándar de la industria para software de escritorio: se compila el ejecutable `.jar` y se realiza el despliegue automático a los servidores de **GitHub Artifacts**.

Si las pruebas son exitosas, el sistema sube el archivo a la nube, dejándolo listo para su descarga e instalación directa por parte de los usuarios, logrando así un flujo real de *Continuous Delivery*.