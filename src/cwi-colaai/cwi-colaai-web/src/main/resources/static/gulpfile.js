var gulp = require("gulp");
var uglify = require("gulp-uglify");
var concat = require("gulp-concat");

var diretorios = {
  scripts: ["js/**/*.js"],
  syles: "css/**/*.css"
};

gulp.task("compiler", function () {
    return gulp.src(diretorios.scripts)
            .pipe(concat("main.min.js"))
            .pipe(uglify())
            .pipe(gulp.dest("bundle"));
});

gulp.task('styles', function () {
    return gulp.src(diretorios.syles)
            .pipe(concat("main.min.css"))
            .pipe(gulp.dest("bundle"));
});

gulp.task("default", ["compiler", "styles"]);