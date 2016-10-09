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
goto localhost:3000

### Start application (prod mode)
```
npm run build
```
goto localhost:9000


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
- [ng2-charts](http://valor-software.com/ng2-charts/)

### Translation
- [ng2-translate](https://github.com/ocombe/ng2-translate)