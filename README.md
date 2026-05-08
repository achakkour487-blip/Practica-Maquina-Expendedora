# Proyecto Máquina Expendedora - Fase 1: Fundamentos y Diseño

## Justificación Técnica

### 1. El Testing E2E vs. Testing Unitario
El **Testing End-to-End (E2E)** es crítico porque valida el flujo completo de la aplicación desde la perspectiva del usuario real, asegurando que todos los componentes (interfaz, lógica y configuración) funcionen juntos correctamente. En este proyecto, permite verificar que un usuario puede interactuar con la máquina expendedora (insertar monedas, ver su saldo y comprar un producto) sin errores visuales o de navegación.

Sin embargo, es más costoso que el unitario por:
* **Complejidad de ejecución**: Requiere levantar el entorno gráfico completo (JavaFX).
* **Tiempo**: Las pruebas E2E son más lentas al simular interacciones humanas como clics y escritura.
* **Fragilidad**: Pequeños cambios en la interfaz (como cambiar el ID de un botón de producto) pueden romper los tests E2E, mientras que los unitarios son más estables y rápidos de ejecutar.

### 2. Continuous Delivery vs. Continuous Deployment
* **Continuous Delivery (Entrega Continua)**: El código se prueba y construye automáticamente, quedando listo para ser desplegado en cualquier momento, pero el paso final a producción requiere una decisión o intervención manual.
* **Continuous Deployment (Despliegue Continuo)**: Todo el proceso está totalmente automatizado. Si el código pasa todas las pruebas en el pipeline de CI/CD (como el configurado en `.github/workflows/main.yml`), se despliega en producción de forma automática sin intervención humana.

### Detalles finales de entrega
* **Cobertura**: Este proyecto ha superado el 50% de cobertura de código requerido mediante las pruebas unitarias de la lógica de negocio y las pruebas E2E de interfaz.
* **Automatización**: Se ha implementado un archivo de workflow en GitHub Actions para ejecutar compilación y tests en cada subida al repositorio de forma autónoma. 