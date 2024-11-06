# SmartContracts-backend

## API Endpoints

| Endpoint                           | Método | Controlador          | Función                                                                                    |
|------------------------------------|--------|-----------------------|-------------------------------------------------------------------------------------------|
| `/api/smartcontract/v1/auth/register`| `POST`  | `AuthController`   | Registra tanto un negocio como un influencer.                                             |
| `/api/smartcontract/v1/auth/login`   | `POST`  | `AuthController`   | Loguea a SmartContracts tanto a un negocio como un influencer.                            |
| `/api/smartcontract/v1/user`         | `GET`   | `UserController`   | Obtiene la lista de todos los usuarios de SmartContracts                                  |
| `/api/smartcontract/v1/user/{userId}` | `GET`  | `UserController`   | Obtiene un usuario por ID                                                                 |
| `/api/smartcontract/v1/user/{userId}` | `PUT`  | `UserController`   | Actualiza un usuario por ID                                                               |
| `/api/smartcontract/v1/services`            | `GET`  | `ServiceController`   | Obtiene la lista de todos los servicios disponibles en Kitchen Tech.                   |
| `/api/smartcontract/v1/service/category/{category}`    | `GET`  | `ServiceController`   | Obtiene los detalles de un servicio específico por una categoría      |
| `/api/smartcontract/v1/service/task/{taskId}`    | `GET`  | `ServiceController`   | Obtiene los detalles de un servicio específico por el ID de una tarea.      |
| `/api/smartcontract/v1/service/user/{userId}`    | `GET`  | `ServiceController`   | Obtiene los detalles de un servicio específico por el ID de un usuario.     |
| `/api/smartcontract/v1/services/{id}`    | `GET`  | `ServiceController`   | Obtiene los detalles de un servicio específico por su ID.                                 |
| `/api/smartcontract/v1/services`         | `POST` | `ServiceController`   | Crea un nuevo servicio en el sistema.                                                     |
| `/api/smartcontract/v1/services/{id}`    | `PUT`  | `ServiceController`   | Actualiza la información de un servicio existente.                                        |
| `/api/smartcontract/v1/services/{id}`    | `DELETE` | `ServiceController`   | Elimina un servicio del sistema por su ID.                                              |
| `/api/smartcontract/v1/reviews`          | `GET`  | `ReviewController`    | Obtiene una lista de todas las reseñas de servicios.                                      |
| `/api/smartcontract/v1/review/service/{serviceId}`      | `GET`  | `ReviewController`    | Obtiene las reseñas específica por el ID de un servicio.             |
| `/api/smartcontract/v1/review/influencer/{influencerId}`| `GET`  | `ReviewController`    | Obtiene las reseñas específica por el ID de un influencer.           |
| `/api/smartcontract/v1/reviews/{id}`     | `GET`  | `ReviewController`    | Obtiene los detalles de una reseña específica por su ID.                                  |
| `/api/smartcontract/v1/reviews/{id}`     | `PUT`  | `ReviewController`    | Actualiza una reseña específica por su ID.                                                |
| `/api/smartcontract/v1/reviews`          | `POST` | `ReviewController`    | Crea una nueva reseña para un servicio.                                                   |
| `/api/smartcontract/v1/contracts`        | `GET`  | `ContractController`  | Obtiene la lista de todos los contratos registrados en el sistema.                        |
| `/api/smartcontract/v1/contract/influencer/{influencerId}`   | `GET`  | `ContractController`  | Obtiene los contratos por el Id de un influencer.               |
| `/api/smartcontract/v1/contract/business/{businessId}`   | `GET`  | `ContractController`  | Obtiene los contratos por un id de business.                        |
| `/api/smartcontract/v1/contracts/{id}`   | `GET`  | `ContractController`  | Obtiene los detalles de un contrato específico por su ID.                                 |
| `/api/smartcontract/v1/contracts`        | `POST` | `ContractController`  | Crea un nuevo contrato en el sistema.                                                     |
| `/api/smartcontract/v1/contracts/{id}`   | `PUT`  | `ContractController`  | Actualiza la información de un contrato existente.                                        |
| `/api/smartcontract/v1/contracts/{id}`   | `DELETE` | `ContractController`  | Elimina un contrato específico del sistema por su ID.                                   |

### Descripción

- **AuthController**: Gestiona las operaciones CRUD para las autenticaciones.
- **UserController**: Gestiona las operaciones CRUD para los usuarios.
- **ServiceController**: Gestiona las operaciones CRUD para los servicios.
- **ReviewController**: Gestiona la creación y recuperación de reseñas para los servicios.
- **ContractController**: Gestiona las operaciones CRUD para los contratos, incluyendo la asociación de servicios a los contratos.
