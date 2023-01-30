## Run GWT

```
mvn gwt:generate-module gwt:devmode
```

## Browse Page

```
http://localhost:8888
```

## Debugging GWT in IDEA

1. Allow unsigned requests

```
Settings -> Build, Execution, Deployment -> Debugger -> Allow unsigned requests
```

2. Edit Configurations -> JavaScript Debug

```
URL:
http://localhost:63342/gwt/gwt-boot-basic/target/gwt/devmode/war/app/index.html
```

3. Config local-remote-mapping (Optional),
   Reference: [Intellij 2018.2 Debugging GWT Apps](https://github.com/GwtMaterialDesign/gwt-material/wiki/Intelij-2018.2-Debugging-GWT-Apps)
