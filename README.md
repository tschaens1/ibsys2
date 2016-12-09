# IBSYS II - ka6 - Handbook

<!-- TOC -->

- [IBSYS II - ka6 - Handbook](#ibsys-ii---ka6---handbook)
    - [Server installation guide](#server-installation-guide)
        - [Java](#java)
        - [Gradle](#gradle)
        - [Maven](#maven)
        - [PostgreSQL](#postgresql)
        - [I don't want an IDE, just run it](#i-dont-want-an-ide-just-run-it)
        - [I have installed Intellij IDEA, how to run it?](#i-have-installed-intellij-idea-how-to-run-it)
        - [Open application in browser](#open-application-in-browser)
    - [Client](#client)
        - [Requirements](#requirements)
        - [Installation](#installation)
        - [Start application (dev mode)](#start-application-dev-mode)
        - [Start application (prod mode)](#start-application-prod-mode)
        - [CSS](#css)
        - [Links:](#links)
            - [Design](#design)
            - [Charts](#charts)

<!-- /TOC -->

---

## Server installation guide
### Java
1. [Download](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html) and install
2. set JAVA_HOME in the environment variables
3. check with `java -version` in the terminal if everything is fine

### Gradle
1. [Download](https://gradle.org/gradle-download/) ("Complete distribution") and extract the downloaded files into your program files.
2. setup the environment variable `GRADLE_HOME` ([read here](https://docs.gradle.org/current/userguide/installation.html#sec:installation_environment_variables)), add `GRADLE_HOME/bin` to path variable. 
3. check the setup with `gradle -v`

### Maven
1. [Download](https://maven.apache.org/download.cgi) and extract into your program files ([read here](https://maven.apache.org/install.html))
2. add the *bin*-path of the extracted folder to your path variable (e.g. `C:\Program Files\apache-maven-3.3.9\bin`)
3. check the installation in a terminal with `mvn -v`

### PostgreSQL
1. [Download](https://www.postgresql.org/download/) and install
2. Setup and **remember** the root username and password
3. Start pgAdmin 4
4. Double click PostgreSQL x.x in the tree view
5. Right click on *Databases* and create a new Databases
6. name the database **ibsys2**
7. Right click on *Login/Group Roles* in the tree view
8. Create a new *Login/Group Role* with name **ibsys2** and (click on tab "Definition") password **ibsys2**. Click on tab *Privileges* and enable all rights for this user.

### I don't want an IDE, just run it
Navigate to the server-folder and run the following command in your terminal:

```
gradle clean build
```
navigate to jar files
```
cd build/libs
```
and run the project
```
java -jar xxx.jar
```

### I have installed Intellij IDEA, how to run it?
1. open Intellij IDEA
2. choose option `Import project` and select the server-folder, press OK
3. choose `Import project from external model` and select `Gradle`, press NEXT
4. select the following checkboxes:
    - "Create separate module per source set"
    - "Use local gradle distribution"
        - check if the `Gradle-home`-path is the folder of your gradle installation
        - Gradle JVM: `Use JAVA_HOME`
        - Project format: `.idea`
5. Press FINISH
6. Wait until Intellij loads the project, may take some minutes...
7. Click in the menu on `View` > `Tool windows` > `Gradle`.
8. The gradle toolbar opens on the right side.
9. Open the task-folder, then build folder and right click on the build-task to run `Run 'server[build]'`.
10. Run the application by right clicking at `src` > `main` > `java` > `de.hska` > `Ibsys2Application` and press the button "Run 'Ibsys2Application'" in the project tree on the left side of the IDE.

### Open application in browser
1. you need to follow the installation instructions for the client (see below).
2. run the command `npm run build` after you installed the node modules with `npm install`. 
3. copy all files from the dist folder into the server-folder: `server` > `src` > `main` > `resources` > `static`. (static needs to be created as a new folder if it does not exist)
4. recompile the java project either with `java -jar xxx.jar` as described above or with Intellij.
5. navigate in your browser to [http://localhost:8080](http://localhost:8080) and you'll see the application

---

## Client

### Requirements
- [Download NodeJS](https://nodejs.org/en/) and install it on your machine 

### Installation
Run the following commands in your terminal which should be opened in the client-folder where you can find the `package.json`-file.
```
npm install
```

### Start application (dev mode)
```
npm start
```
goto localhost:3000

### Start application (prod mode)
```
npm run build
```
You need to copy all files from the dist-folder into the server-folder: `server` > `src` > `main` > `resources` > `static`. (static needs to be created as a new folder if it does not exist)


### CSS
CSS-Preprocessor: SASS

Change local styles:
```
src/components/<componentName>/<componentName>.component.scss
```

Change global styles:
```
src/styles/styles.global.scss
```

Change colors:
```
src/styles/colors.scss
```
```css
$theme-color: #01579b;
```

Use breakpoints:
```
src/styles/breakpoints.scss
```
Use them in the component's sass-files:
```css
@import '../../styles/breakpoints';

.sample{
    widht: 50%;
    height: 50%;

    @include breakpoint(mobileonly){
        width: 100%;
        height: 100%
    }
}
```

Use colors in component styles:
```css
@import '../../styles/colors';

background-color: $theme-color;
```

### Links:
- [MaterializeCSS](http://materializecss.com/)
- [Angular 2 Material](https://material.angular.io/)
- [Material Design Icons](https://materialdesignicons.com/)
- [ChartJS](http://www.chartjs.org/)
<<<<<<< HEAD
- [ng2-charts](http://valor-software.com/ng2-charts/)

### Translation
- [ng2-translate](https://github.com/ocombe/ng2-translate)
=======
- [ng2-charts](http://valor-software.com/ng2-charts/)
>>>>>>> 7e04808... added installation guide for server
