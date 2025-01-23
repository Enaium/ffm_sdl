$sdl_path = "SDL"
mkdir "$sdl_path/build";cmake -DCMAKE_BUILD_TYPE=Release .. -B "$sdl_path/build";cmake --build . --config Release --parallel -B "$sdl_path/build"
jextract --include-dir "$sdl_path/include" --dump-includes "$sdl_path/build/includes.txt" "$sdl_path/include/SDL3/SDL.h"
jextract --include-dir "$sdl_path/include" --output src/main/java --target-package org.libsdl --library SDL3 --use-system-load-library "@$sdl_path/build/includes.txt" "$sdl_path/include/SDL3/SDL.h"