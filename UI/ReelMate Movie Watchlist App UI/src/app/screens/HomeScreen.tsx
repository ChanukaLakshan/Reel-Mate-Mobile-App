import { useState } from "react";
import { useNavigate } from "react-router";
import { Search, Bell, Plus, Check, User, List } from "lucide-react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";

const movies = [
  {
    id: 1,
    title: "Midnight Shadows",
    poster: "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3ZpZSUyMHBvc3RlciUyMGNpbmVtYSUyMGRhcmt8ZW58MXx8fHwxNzcxODgwMDg3fDA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "Thriller",
    year: "2024",
    rating: 4.5,
  },
  {
    id: 2,
    title: "Beyond the Stars",
    poster: "https://images.unsplash.com/photo-1619960535209-fc795018bbe1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzY2ktZmklMjBmdXR1cmlzdGljJTIwc2NlbmV8ZW58MXx8fHwxNzcxODI1NTI5fDA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "Sci-Fi",
    year: "2024",
    rating: 4.8,
  },
  {
    id: 3,
    title: "Love in Paris",
    poster: "https://images.unsplash.com/photo-1708787788824-07d6d97b0111?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMG1vdmllJTIwY291cGxlfGVufDF8fHx8MTc3MTgyNDAxN3ww&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "Romance",
    year: "2024",
    rating: 4.3,
  },
  {
    id: 4,
    title: "Dark Manor",
    poster: "https://images.unsplash.com/photo-1767048264833-5b65aacd1039?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxob3Jyb3IlMjBtb3ZpZSUyMGF0bW9zcGhlcmV8ZW58MXx8fHwxNzcxODgwMDg4fDA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "Horror",
    year: "2023",
    rating: 4.1,
  },
  {
    id: 5,
    title: "The Last Stand",
    poster: "https://images.unsplash.com/photo-1645808651017-c5e3018553c7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBtb3ZpZSUyMHNjZW5lfGVufDF8fHx8MTc3MTg4MDA4N3ww&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "Action",
    year: "2024",
    rating: 4.6,
  },
  {
    id: 6,
    title: "Laugh Out Loud",
    poster: "https://images.unsplash.com/photo-1604674725989-52c312835516?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21lZHklMjBoYXBweSUyMHBlb3BsZXxlbnwxfHx8fDE3NzE4NDM3ODh8MA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "Comedy",
    year: "2024",
    rating: 4.4,
  },
];

export function HomeScreen() {
  const navigate = useNavigate();
  const [watchlist, setWatchlist] = useState<number[]>([]);
  const [watched, setWatched] = useState<number[]>([]);

  const toggleWatchlist = (id: number) => {
    setWatchlist((prev) =>
      prev.includes(id) ? prev.filter((i) => i !== id) : [...prev, id]
    );
  };

  const toggleWatched = (id: number) => {
    setWatched((prev) =>
      prev.includes(id) ? prev.filter((i) => i !== id) : [...prev, id]
    );
  };

  return (
    <div className="min-h-screen bg-[#121212]">
      {/* Top App Bar */}
      <div className="sticky top-0 z-50 bg-[#1E1E1E] border-b border-[#2A2A2A] px-4 py-4">
        <div className="flex items-center justify-between max-w-[360px] mx-auto">
          <h1 className="text-2xl font-bold text-white">ReelMate</h1>
          <div className="flex items-center gap-3">
            <button
              onClick={() => navigate("/my-lists")}
              className="p-2 hover:bg-[#242424] rounded-full transition-colors"
            >
              <List className="w-6 h-6 text-white" />
            </button>
            <button className="relative p-2 hover:bg-[#242424] rounded-full transition-colors">
              <Bell className="w-6 h-6 text-white" />
              <span className="absolute top-1 right-1 w-2 h-2 bg-[#E50914] rounded-full"></span>
            </button>
            <button
              onClick={() => navigate("/profile")}
              className="w-10 h-10 rounded-full bg-[#242424] flex items-center justify-center hover:bg-[#2A2A2A] transition-colors"
            >
              <User className="w-5 h-5 text-white" />
            </button>
          </div>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 pb-6">
        {/* Search Bar */}
        <div className="py-4">
          <div className="relative">
            <Search className="absolute left-4 top-1/2 transform -translate-y-1/2 w-5 h-5 text-[#B3B3B3]" />
            <Input
              type="text"
              placeholder="Search movies..."
              className="pl-12 h-12 bg-[#1E1E1E] border-[#2A2A2A] text-white placeholder:text-[#B3B3B3] rounded-2xl focus:border-[#E50914] focus:ring-[#E50914]"
            />
          </div>
        </div>

        {/* Section Header */}
        <div className="mb-4">
          <h2 className="text-xl font-bold text-white">My Watchlist</h2>
          <p className="text-[#B3B3B3] text-sm font-light mt-1">
            {watchlist.length} movies in your watchlist
          </p>
        </div>

        {/* Movie Cards Grid */}
        <div className="space-y-4">
          {movies.map((movie) => (
            <div
              key={movie.id}
              className="bg-[#1E1E1E] rounded-2xl overflow-hidden shadow-lg hover:shadow-xl transition-shadow"
            >
              <div className="flex gap-4 p-4">
                {/* Movie Poster */}
                <div
                  onClick={() => navigate(`/movie/${movie.id}`)}
                  className="cursor-pointer flex-shrink-0"
                >
                  <img
                    src={movie.poster}
                    alt={movie.title}
                    className="w-24 h-36 object-cover rounded-xl"
                  />
                </div>

                {/* Movie Info */}
                <div className="flex-1 flex flex-col justify-between">
                  <div>
                    <h3
                      onClick={() => navigate(`/movie/${movie.id}`)}
                      className="text-lg font-semibold text-white mb-1 cursor-pointer hover:text-[#E50914] transition-colors"
                    >
                      {movie.title}
                    </h3>
                    <div className="flex items-center gap-2 mb-2">
                      <span className="text-[#F5C518] text-sm font-medium">
                        ★ {movie.rating}
                      </span>
                      <span className="text-[#B3B3B3] text-sm">•</span>
                      <span className="text-[#B3B3B3] text-sm">{movie.year}</span>
                    </div>
                    <span className="inline-block bg-[#242424] text-[#B3B3B3] text-xs px-3 py-1 rounded-full">
                      {movie.genre}
                    </span>
                  </div>

                  {/* Action Buttons */}
                  <div className="flex gap-2 mt-3">
                    <Button
                      onClick={() => toggleWatchlist(movie.id)}
                      size="sm"
                      className={`flex-1 rounded-xl h-9 ${
                        watchlist.includes(movie.id)
                          ? "bg-[#242424] text-white border border-[#2A2A2A] hover:bg-[#2A2A2A]"
                          : "bg-[#E50914] text-white hover:bg-[#B20710]"
                      }`}
                    >
                      {watchlist.includes(movie.id) ? (
                        <>
                          <Check className="w-4 h-4 mr-1" />
                          Listed
                        </>
                      ) : (
                        <>
                          <Plus className="w-4 h-4 mr-1" />
                          List
                        </>
                      )}
                    </Button>
                    <Button
                      onClick={() => toggleWatched(movie.id)}
                      size="sm"
                      className={`flex-1 rounded-xl h-9 ${
                        watched.includes(movie.id)
                          ? "bg-[#22C55E] text-white hover:bg-[#16A34A]"
                          : "bg-[#242424] text-white border border-[#2A2A2A] hover:bg-[#2A2A2A]"
                      }`}
                    >
                      {watched.includes(movie.id) ? (
                        <>
                          <Check className="w-4 h-4 mr-1" />
                          Watched
                        </>
                      ) : (
                        "Mark"
                      )}
                    </Button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}