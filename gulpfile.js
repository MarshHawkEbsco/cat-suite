var gulp = require('gulp'),
    browserify = require('browserify'),
    babelify = require('babelify'),
    envify = require('envify'),
    uglifyify = require('uglifyify'),
    source = require('vinyl-source-stream'),
    webserver = require('gulp-webserver'),
    minimist = require('minimist'),
    buffer = require('gulp-buffer'),
    uglify = require('gulp-uglify'),
    gutil = require('gulp-util');

var src = './source',
    app = './builds/app',
    options = minimist(process.argv),
    environment = options.environment || 'development';

function handleError(err) {
    console.error('Error!', err.message);
    this.emit('end');
}

gulp.task('js', function () {
    return browserify(src + '/js/app.js', { debug: true })
        .on('error', handleError)
        .transform(babelify, { presets: ["env", "react", "stage-0"] })
        .on('error', handleError)
        .bundle()
        .on('error', handleError)
        .pipe(source('app.js'))
        .pipe(gulp.dest(app + '/js'));
});

gulp.task('deploy', function () {
    return browserify(src + '/js/app.js', {
        debug: true, transform: [
            ['envify', { NODE_ENV: 'production', global: true }]
        ]
    })
        .on('error', handleError)
        .transform(babelify, { presets: ["env", "react", "stage-0"] })
        .on('error', handleError)
        .transform(envify)
        .on('error', handleError)
        .transform(uglifyify)
        .on('error', handleError)
        .bundle()
        .on('error', handleError)
        .pipe(source('deploy-test.min.js'))
        .pipe(buffer())
        .pipe(uglify())
        .pipe(gulp.dest(app + '/dist/'));
});

gulp.task('html', function () {
    gulp.src(app + '/**/*.html');
});

gulp.task('css', function () {
    gulp.src(app + '/css/*.css');
});

gulp.task('watch', function () {
    gulp.watch(src + '/js/**/*.js', ['js']);
    gulp.watch(app + '/css/**/*.css', ['css']);
    gulp.watch([app + '/**/*.html'], ['html']);
});

gulp.task('webserver', function () {
    gulp.src(app + '/')
        .pipe(webserver({
            livereload: true,
            open: true,
            port: 8765
        }));
});

gulp.task('default', ['watch', 'html', 'js', 'css', 'webserver']);