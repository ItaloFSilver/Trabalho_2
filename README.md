# Trabalho_2
Repositório para desenvolvimento do segundo trabalho de Orientação a Objetos

# 🏥 Documentação Técnica Unificada - Sistema Médico

## 📦 Pacote: `CREDENTIALS`
---
### Class: **Email**
- **Herança:** `Nenhuma`

**Atributos:** `email`, `regex`, `validateEmail`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `getEmail` | `String` | - |

---
### Class: **Password**
- **Herança:** `Nenhuma`

**Atributos:** `password`, `validatePassword`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `getPassword` | `String` | - |

---
### Class: **PhoneNumber**
- **Herança:** `Nenhuma`

**Atributos:** `phoneNumber`, `String`, `validatephoneNumber`, `normalize`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |

---
### Class: **CPF**
- **Herança:** `Nenhuma`

**Atributos:** `cpf`, `regex`, `getCPF`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `normalize` | `String` | - |

---
## 📦 Pacote: `USERS`
---
### Class: **Secretary**
- **Herança:** `User`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `createPanel` | `JPanel` | - |
| `getStatus` | `boolean` | - |
| `setStatus` | `void` | - |

---
### Class: **User**
- **Herança:** `Nenhuma`

**Atributos:** `profile`, `name`, `email`, `cpf`, `password`, `phoneNumber`, `status`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `createPanel` | `JPanel` | - |
| `getName` | `String` | - |
| `getFormatEmail` | `Email` | - |
| `getEmail` | `String` | - |
| `getFormatPassword` | `Password` | - |
| `getPassword` | `String` | - |
| `getCPF` | `CPF` | - |
| `getphoneNumber` | `PhoneNumber` | - |
| `getProfile` | `Profile` | - |
| `setStatus` | `void` | - |
| `getStatus` | `boolean` | - |

---
### Enum: **Specialization**
- **Herança:** `Nenhuma`

**Atributos:** `text`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `fromString` | `Specialization` | - |

---
### Class: **Patient**
- **Herança:** `User`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `createPanel` | `JPanel` | - |
| `getStatus` | `boolean` | - |
| `setStatus` | `void` | - |
| `setHospitalized` | `void` | - |
| `isHospitalized` | `boolean` | - |

---
### Enum: **Profile**
- **Herança:** `Nenhuma`

**Atributos:** `label`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `fromString` | `Profile` | - |

---
### Class: **Medic**
- **Herança:** `User`

**Atributos:** `active`, `agenda`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `getDisponibilityAsList` | `List<WorkShift>` | - |
| `createPanel` | `JPanel` | - |
| `getStatus` | `boolean` | - |
| `setStatus` | `void` | - |
| `getFreeTime` | `List<String>` | - |

---
## 📦 Pacote: `SERVICES`
---
### Class: **WorkShift**
- **Herança:** `Nenhuma`

**Atributos:** `dayOfWeek`, `start`, `end`, `medicCPF`, `block`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `getDayOfWeek` | `DayOfWeek` | - |
| `getDayOfWeeki` | `int` | - |
| `getStart` | `String` | - |
| `timeBlock` | `void` | - |
| `setFree` | `void` | - |
| `getFreeTime` | `List<String>` | - |
| `isFree` | `boolean` | - |
| `getEnd` | `String` | - |
| `getMedicCPF` | `CPF` | - |

---
### Class: **Appointment**
- **Herança:** `Nenhuma`

**Atributos:** `medicCPF`, `patientCPF`, `date`, `confirmed`, `medic`, `patient`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `getMedicName` | `String` | - |
| `getMedic` | `Medic` | - |
| `getPatient` | `Patient` | - |
| `getMedicCPF` | `CPF` | - |
| `getPatientName` | `String` | - |
| `getPatientCPF` | `CPF` | - |
| `getData` | `Date` | - |
| `getDate` | `String` | - |
| `getDayOfWeek` | `int` | - |
| `getCheck` | `String` | - |

---
### Class: **MedicalDocument**
- **Herança:** `Nenhuma`

**Atributos:** `tipo`, `doctorCpf`, `patientCpf`, `diagnostico`, `recomendacao`, `dataEmissao`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `getTipo` | `String` | - |
| `getDoctorCpf` | `String` | - |
| `getPatientCpf` | `String` | - |
| `getDiagnostico` | `String` | - |
| `getRecomendacao` | `String` | - |
| `getDataEmissao` | `Date` | - |

---
### Enum: **DayOfWeek**
- **Herança:** `Nenhuma`

**Atributos:** `text`, `order`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `fromString` | `DayOfWeek` | - |

---
## 📦 Pacote: `EXCEPTIONS`
---
### Class: **InvalidAppointmentException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidEmailException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidPasswordException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidRemoveException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidCPFException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidphoneNumberException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidLoginException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidDateException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidRegisterException**
- **Herança:** `RuntimeException`

---
### Class: **InvalidSaveException**
- **Herança:** `RuntimeException`

---
## 📦 Pacote: `REPOSITORY`
---
### Class: **BaseRepository**
- **Herança:** `Nenhuma`

**Atributos:** `path`, `gson`, `type`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `save` | `void` | - |
| `remove` | `void` | - |
| `listAll` | `List<User>` | - |

---
### Class: **UserAdapter**
- **Herança:** `Nenhuma`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `deserialize` | `User` | `JsonParseException ` |

---
### Class: **WorkShiftRepository**
- **Herança:** `Nenhuma`

**Atributos:** `path`, `gson`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `save` | `void` | `InvalidDateException` |
| `remove` | `void` | - |
| `listAll` | `List<WorkShift>` | - |
| `searchByCPF` | `List<WorkShift>` | - |

---
### Interface: **Repository**
- **Herança:** `Nenhuma`

---
### Class: **PatientRepository**
- **Herança:** `BaseRepository<Patient>`

---
### Class: **AppointmentRepository**
- **Herança:** `Nenhuma`

**Atributos:** `path`, `gson`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `save` | `void` | - |
| `remove` | `void` | - |
| `listAll` | `List<Appointment>` | - |
| `searchByCPF` | `Appointment` | - |
| `searchByCPF` | `Appointment` | - |

---
### Class: **UserRepository**
- **Herança:** `Nenhuma`

**Atributos:** `extends`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `saveUser` | `void` | - |
| `saveUser` | `void` | `InvalidSaveException ` |
| `findByCPF` | `User` | - |
| `listAllUsers` | `List<User>` | - |
| `removeUser` | `void` | `InvalidRemoveException` |
| `removeUserByCPF` | `void` | `InvalidRemoveException` |

---
### Class: **SecretaryRepository**
- **Herança:** `BaseRepository<Secretary>`

---
### Class: **MedicRepository**
- **Herança:** `BaseRepository<Medic>`

---
## 📦 Pacote: `CONTROLLER`
---
### Class: **MedicController**
- **Herança:** `Nenhuma`

**Atributos:** `repoWS`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `savesWorkShift` | `void` | `InvalidDateException ` |
| `removesWorkShift` | `void` | `InvalidDateException ` |
| `removesWorkShift` | `void` | - |
| `loadWorkShift` | `List<WorkShift>` | - |
| `freeTime` | `void` | - |
| `lockTime` | `void` | - |
| `medicoAtendeNestaData` | `boolean` | - |

---
### Class: **AppointmentController**
- **Herança:** `Nenhuma`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `saveAppointment` | `void` | `InvalidAppointmentException ` |
| `removeAppointment` | `void` | - |
| `listAll` | `List<Appointment>` | - |
| `removeAllOfUser` | `void` | - |
| `listThis` | `List<Appointment>` | - |

---
### Class: **SecretaryController**
- **Herança:** `Nenhuma`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `listAllUsers` | `List<User>` | - |
| `listPatients` | `List<User>` | - |
| `listMedics` | `List<User>` | - |
| `removeUserByCPF` | `void` | `InvalidRemoveException ` |
| `findUserByCPF` | `User` | - |
| `setStatus` | `void` | - |
| `checkStatus` | `boolean` | - |

---
### Class: **RegisterController**
- **Herança:** `Nenhuma`

**Atributos:** `regex`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `registerUser` | `void` | `InvalidRegisterException ` |

---
### Class: **LoginController**
- **Herança:** `Nenhuma`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `login` | `User` | `InvalidLoginException ` |

---
### Class: **DocumentController**
- **Herança:** `Nenhuma`

**Atributos:** `documentos`, `path`, `gson`, `salvar`, `carregar`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `emitirDocumento` | `void` | - |
| `buscarPorCPF` | `List<MedicalDocument>` | `InvalidCPFException ` |

---
## 📦 Pacote: `VIEW`
---
### Class: **MainFrame**
- **Herança:** `javax.swing.JFrame`

**Atributos:** `java`, `cardLayout`, `jPanel`, `user`, `secretary`, `patient`, `medic`, `initComponents`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `changeScreen` | `void` | - |
| `changeScreen` | `void` | - |
| `getUser` | `User` | - |
| `main` | `void` | - |

---
### Class: **UserPanel**
- **Herança:** `JPanel`

**Atributos:** `mainPage`, `logOutBtn`, `tabbedPane`, `user`, `createPersonalDataTab`, `initDataComponents`, `logOutBtnActionPerformed`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `actionPerformed` | `void` | - |

---
### Class: **LoginPanel**
- **Herança:** `JPanel`

**Atributos:** `campoUser`, `senhaUser`, `enterBtn`, `exitBtn`, `userLabel`, `passwordLabel`, `invalidCredLbl`, `mainFrame`, `user`, `limpaCampo`, `enterBtnActionPerformed`, `exitBtnActionPerformed`

---
### Class: **SecretaryPanel**
- **Herança:** `UserPanel<Secretary>`

**Atributos:** `logOutBtn`, `mainPage`, `controller`, `model`, `criarTabAgenda`, `criarTabUsuarios`, `openRegisterWindow`, `deleteAppointments`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `isCellEditable` | `boolean` | - |
| `actionPerformed` | `void` | - |
| `actionPerformed` | `void` | - |
| `actionPerformed` | `void` | - |

---
### Class: **MedicPanel**
- **Herança:** `UserPanel<Medic>`

**Atributos:** `consultController`, `appoint`, `agenda`, `listModel`, `listaVisual`, `comboDias`, `spinnerInicio`, `spinnerFim`, `user`, `createAppointmentPage`, `updateBtnActionListener`, `createHourSpinner`, `addItemInList`, `criarCampoFormatado`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `createPersonalDataTab` | `JPanel` | - |
| `isCellEditable` | `boolean` | - |
| `DoctorSchedulePanel` | `JPanel` | - |
| `getHour` | `String` | - |
| `createDoctorIssuePanel` | `JPanel` | - |

---
### Class: **PatientPanel**
- **Herança:** `UserPanel<Patient>`

**Atributos:** `consultController`, `agenda`, `appoint`, `user`, `listaMeusDocs`, `documentController`, `createAppointmentPage`, `updateBtnActionListener`, `createPatientsList`, `carregarDados`, `mostrarDetalhes`, `initTables`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `isCellEditable` | `boolean` | - |
| `isCellEditable` | `boolean` | - |
| `hideWindow` | `void` | - |
| `createDocumentPanel` | `JPanel` | - |
| `isCellEditable` | `boolean` | - |
| `mouseClicked` | `void` | - |

---
### Class: **RegisterPanel**
- **Herança:** `JPanel`

**Atributos:** `cbTipoUsuario`, `campoCPF`, `campophoneNumber`, `campoNome`, `campoEmail`, `campoSenha`, `regPage`, `main`, `adicionarCampo`, `criarCampoFormatado`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `actionPerformed` | `void` | - |
| `actionPerformed` | `void` | - |
| `setText` | `void` | - |
| `removePerIndex` | `void` | - |

---
### Class: **EditAppointmentDialog**
- **Herança:** `JDialog`

**Atributos:** `comboHorarios`, `salvou`, `desmarcou`, `novoHorarioSelecionado`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `isSalvou` | `boolean` | - |
| `isDesmarcou` | `boolean` | - |
| `getNovoHorario` | `String` | - |

---
### Class: **RegisterFrame**
- **Herança:** `JFrame`

**Atributos:** `Credentials`

---
### Class: **AppointmentPanel**
- **Herança:** `JPanel`

**Atributos:** `comboMedico`, `comboPaciente`, `spinnerDataHora`, `checkConfirmada`, `controller`, `model`, `frame`, `comboHorario`, `saveAppointment`, `cancelSaving`, `clearFields`, `closeWindow`

---
## 📦 Pacote: `GERAL`
---
### Class: **Main**
- **Herança:** `Nenhuma`

| Método | Retorno | Exceções |
| :--- | :--- | :--- |
| `main` | `void` | - |

---
### Class: **PasswordTest**
- **Herança:** `Nenhuma`

---
### Class: **EmailTest**
- **Herança:** `Nenhuma`

---
### Class: **CPFTest**
- **Herança:** `Nenhuma`

---
### Class: **PhoneNumberTest**
- **Herança:** `Nenhuma`

---

