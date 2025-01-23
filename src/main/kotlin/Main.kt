import org.libsdl.*
import org.libsdl.SDL_h_1.*
import java.lang.foreign.Arena

/**
 * @author Enaium
 */
fun main() {
    Arena.ofConfined().use {
        val init = SDL_h_2.SDL_Init(SDL_INIT_VIDEO() and SDL_INIT_EVENTS())

        if (!init) {
            println("SDL_Init Error: ${SDL_h_3.SDL_GetError()}")
            return
        }

        val windowPtr = it.allocate(C_POINTER)
        val rendererPtr = it.allocate(C_POINTER)

        SDL_CreateWindowAndRenderer(it.allocateFrom("Hello World"), 640, 480, 0, windowPtr, rendererPtr)

        val window = windowPtr.get(C_POINTER, 0)
        val renderer = rendererPtr.get(C_POINTER, 0)

        val rect = SDL_FRect.allocate(it)
        SDL_FRect.x(rect, 100f)
        SDL_FRect.y(rect, 100f)
        SDL_FRect.w(rect, 440f)
        SDL_FRect.h(rect, 280f)


        val event = SDL_Event.allocate(it)
        var quit = false
        while (!quit) {
            while (SDL_PollEvent(event)) {
                when (SDL_Event.type(event)) {
                    SDL_EVENT_QUIT() -> {
                        quit = true
                    }

                    SDL_EVENT_KEY_DOWN() -> {
                        when (SDL_KeyboardEvent.key(SDL_Event.key(event))) {
                            SDLK_UP() -> {
                                SDL_FRect.y(rect, SDL_FRect.y(rect) - 5)
                            }

                            SDLK_DOWN() -> {
                                SDL_FRect.y(rect, SDL_FRect.y(rect) + 5)
                            }

                            SDLK_LEFT() -> {
                                SDL_FRect.x(rect, SDL_FRect.x(rect) - 5)
                            }

                            SDLK_RIGHT() -> {
                                SDL_FRect.x(rect, SDL_FRect.x(rect) + 5)
                            }
                        }
                    }
                }
            }

            SDL_h_2.SDL_SetRenderDrawColor(renderer, 33.toByte(), 33.toByte(), 33.toByte(), 255.toByte())
            SDL_h_2.SDL_RenderClear(renderer)

            SDL_h_2.SDL_SetRenderDrawColor(renderer, 0.toByte(), 0.toByte(), 255.toByte(), 255.toByte())

            SDL_h_2.SDL_RenderFillRect(renderer, rect)

            SDL_h_2.SDL_RenderPresent(renderer)
        }

        SDL_h_2.SDL_DestroyRenderer(renderer)
        SDL_h_3.SDL_DestroyWindow(window)
    }
}