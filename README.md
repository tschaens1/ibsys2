# ibsys2

## Client

### Installation
```
npm install
```

### Start application (dev mode)
```
npm start
```
goto localhost:8080

### Start application (prod mode)
```
npm run build
```
goto localhost:9000


### CSS
CSS-Preprocessor: SASS

Change local styles:
```
src/<componentName>/<componentName>.component.scss
```

Change global styles:
```
public/scss/styles.global.scss
```

Change colors:
```
public/scss/colors.scss
```
```css
$theme-color: #01579b;
```

Use colors in component styles:
```css
@import '../../public/scss/colors';

background-color: $theme-color;
```


### Links:
- [MaterializeCSS](http://materializecss.com/)
- [Google Icons](https://design.google.com/icons/)

### Translation
[Translation](https://github.com/ocombe/ng2-translate)