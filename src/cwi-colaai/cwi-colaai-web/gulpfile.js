var gulp = require("gulp");
var uglify = require("gulp-uglify");
var concat = require("gulp-concat");
var cleanCSS = require('gulp-clean-css');

var diretorios = {
  scripts: [
      "js/features/**/*.js",
      "js/site/custom/**/*.js",
      "js/site/service/**/*.js",
      "js/site/model/**/*.js",
      "js/site/controller/**/*.js",
      "js/site/view/**/*.js"
  ],
  styles: [
      "css/features/**/*.css", 
      "css/site/**/*.css"
  ]
};

gulp.task("scripts", function () {
    return gulp.src(diretorios.scripts)
            .pipe(concat("main.min.js"))
            .pipe(uglify())
            .pipe(gulp.dest("bundle"));
});

gulp.task('styles', function () {
    return gulp.src(diretorios.styles)
            .pipe(concat("main.min.css"))
            .pipe(cleanCSS({processImport: false}))
            .pipe(gulp.dest("bundle"));
});

gulp.task("watch", function() {
    return gulp.watch(diretorios.styles.concat(diretorios.scripts), ["scripts", "styles"]);
});

gulp.task("default", ["scripts", "styles"]);