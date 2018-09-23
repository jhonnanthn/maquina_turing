
import Taskerify from 'taskerify';

Taskerify.config.sourcemaps    = false;
Taskerify.config.srcPath       = './src/assets';  // Src Path
Taskerify.config.distPath      = './dist/html/assets'; // Dist Path
Taskerify.config.srcViewsPath  = './src';         // Views Src Path
Taskerify.config.distViewsPath = './dist';        // Compiled Views Dist Path (HTML)

const NODE_MODULES = './node_modules';
const SRC          = Taskerify.config.srcPath;
const DIST         = Taskerify.config.distPath;
const commomFiles  = ['turing'];

Taskerify((mix) => {
    mix.pug();

    commomFiles.map((file) => {
        mix.browserify(`${SRC}/js/${file}.js`, `${DIST}/js`)
            .sass(`${SRC}/scss/${file}.scss`,  `${DIST}/css`);
    });
});
