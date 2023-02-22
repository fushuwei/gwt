# Build Project

`cd PROJECT_ROOT_DIR`

`mvn clean package`

# Run Project

`cd PROJECT_ROOT_DIR`

`mvn gwt:generate-module gwt:devmode`

## Browse Page

`http://localhost:8888`

# Debugging GWT in IDEA

1. Edit Configurations -> JavaScript Debug -> URL

`http://localhost:8888/app/` *OR* `http://localhost:63342/gwt/gwt-samples/gwt-sample-gwtboot/target/gwt/devmode/war/app/index.html`

2. Allow unsigned requests (Optional, Must be configured only when first step config url as http://<span></span>localhost:63342/...)

`Settings -> Build, Execution, Deployment -> Debugger -> Allow unsigned requests`

3. Config local-remote-mapping (Optional)

   Reference: [Intellij 2018.2 Debugging GWT Apps](https://github.com/GwtMaterialDesign/gwt-material/wiki/Intelij-2018.2-Debugging-GWT-Apps)
