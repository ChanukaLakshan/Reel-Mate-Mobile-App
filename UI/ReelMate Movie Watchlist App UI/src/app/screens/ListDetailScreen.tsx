import { useState } from "react";
import { useNavigate, useParams } from "react-router";
import { ArrowLeft, Plus, Trash2, Share2, Check, Film } from "lucide-react";
import { Button } from "../components/ui/button";
import { motion, AnimatePresence } from "motion/react";

const listData = {
  id: 1,
  name: "My Favorite Thrillers",
  description: "Edge-of-your-seat suspense movies that keep you guessing until the end",
  createdDate: "Jan 15, 2026",
  movies: [
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
      title: "Silent Night",
      poster: "https://images.unsplash.com/photo-1563905463861-7d77975b3a44?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0aHJpbGxlciUyMHN1c3BlbnNlJTIwZGFya3xlbnwxfHx8fDE3NzE3NjI5MjB8MA&ixlib=rb-4.1.0&q=80&w=1080",
      genre: "Mystery",
      year: "2023",
      rating: 4.2,
    },
    {
      id: 3,
      title: "Dark Manor",
      poster: "https://images.unsplash.com/photo-1767048264833-5b65aacd1039?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxob3Jyb3IlMjBtb3ZpZSUyMGF0bW9zcGhlcmV8ZW58MXx8fHwxNzcxODgwMDg4fDA&ixlib=rb-4.1.0&q=80&w=1080",
      genre: "Horror",
      year: "2023",
      rating: 4.1,
    },
  ],
};

export function ListDetailScreen() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [movies, setMovies] = useState(listData.movies);
  const [addedToWatchlist, setAddedToWatchlist] = useState(false);

  const handleRemoveMovie = (movieId: number) => {
    setMovies((prev) => prev.filter((movie) => movie.id !== movieId));
  };

  const handleAddAllToWatchlist = () => {
    setAddedToWatchlist(true);
    setTimeout(() => setAddedToWatchlist(false), 2000);
  };

  return (
    <div className="min-h-screen bg-[#121212]">
      {/* Header */}
      <div className="sticky top-0 z-50 bg-[#121212]/95 backdrop-blur-sm border-b border-[#2A2A2A] px-4 py-4">
        <div className="max-w-[360px] mx-auto flex items-center gap-4">
          <button
            onClick={() => navigate("/my-lists")}
            className="p-2 hover:bg-[#242424] rounded-full transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-white" />
          </button>
          <h1 className="text-lg font-semibold text-white truncate flex-1">List Details</h1>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 py-6">
        {/* List Header */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="bg-gradient-to-br from-[#E50914]/20 to-[#1E1E1E] rounded-2xl p-6 mb-6 border border-[#E50914]/30 shadow-xl"
        >
          <h2 className="text-2xl font-bold text-white mb-2">{listData.name}</h2>
          <p className="text-[#B3B3B3] font-light mb-4">
            {listData.description}
          </p>
          <div className="flex items-center gap-3 text-sm">
            <div className="flex items-center gap-1.5">
              <Film className="w-4 h-4 text-[#F5C518]" />
              <span className="text-[#B3B3B3] font-light">
                {movies.length} {movies.length === 1 ? "movie" : "movies"}
              </span>
            </div>
            <span className="text-[#2A2A2A]">•</span>
            <span className="text-[#B3B3B3] font-light text-xs">
              {listData.createdDate}
            </span>
          </div>
        </motion.div>

        {/* Action Buttons */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
          className="grid grid-cols-3 gap-3 mb-6"
        >
          <motion.div whileTap={{ scale: 0.95 }}>
            <Button
              onClick={() => navigate("/list-selection")}
              className="w-full h-12 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-xl shadow-lg shadow-[#E50914]/30 flex flex-col items-center justify-center gap-1 py-2"
            >
              <Plus className="w-5 h-5" />
              <span className="text-xs">Add</span>
            </Button>
          </motion.div>

          <motion.div whileTap={{ scale: 0.95 }}>
            <Button
              onClick={handleAddAllToWatchlist}
              disabled={movies.length === 0}
              className="w-full h-12 bg-[#242424] hover:bg-[#2A2A2A] text-white font-semibold rounded-xl border border-[#2A2A2A] flex flex-col items-center justify-center gap-1 py-2 disabled:opacity-50"
            >
              {addedToWatchlist ? (
                <>
                  <Check className="w-5 h-5 text-[#22C55E]" />
                  <span className="text-xs">Added</span>
                </>
              ) : (
                <>
                  <Check className="w-5 h-5" />
                  <span className="text-xs">Watchlist</span>
                </>
              )}
            </Button>
          </motion.div>

          <motion.div whileTap={{ scale: 0.95 }}>
            <Button
              className="w-full h-12 bg-[#242424] hover:bg-[#2A2A2A] text-white font-semibold rounded-xl border border-[#2A2A2A] flex flex-col items-center justify-center gap-1 py-2"
            >
              <Share2 className="w-5 h-5" />
              <span className="text-xs">Share</span>
            </Button>
          </motion.div>
        </motion.div>

        {/* Movies List */}
        {movies.length > 0 ? (
          <div className="space-y-4">
            <h3 className="text-lg font-bold text-white mb-4">Movies in this list</h3>
            <AnimatePresence>
              {movies.map((movie, index) => (
                <motion.div
                  key={movie.id}
                  initial={{ opacity: 0, x: -20 }}
                  animate={{ opacity: 1, x: 0 }}
                  exit={{ opacity: 0, x: 20, height: 0 }}
                  transition={{ duration: 0.3, delay: index * 0.05 }}
                  className="bg-[#1E1E1E] rounded-2xl overflow-hidden border border-[#2A2A2A] hover:border-[#E50914]/30 transition-all shadow-lg"
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
                        className="w-24 h-36 object-cover rounded-xl hover:scale-105 transition-transform"
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

                      {/* Remove Button */}
                      <motion.div whileTap={{ scale: 0.95 }}>
                        <Button
                          onClick={() => handleRemoveMovie(movie.id)}
                          size="sm"
                          className="w-full mt-3 h-9 bg-[#242424] text-[#EF4444] hover:bg-[#EF4444]/20 rounded-xl border border-[#2A2A2A] hover:border-[#EF4444]/30"
                        >
                          <Trash2 className="w-4 h-4 mr-1" />
                          Remove
                        </Button>
                      </motion.div>
                    </div>
                  </div>
                </motion.div>
              ))}
            </AnimatePresence>
          </div>
        ) : (
          // Empty State
          <motion.div
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
            className="text-center py-16 bg-[#1E1E1E] rounded-2xl border border-[#2A2A2A]"
          >
            <div className="w-24 h-24 bg-[#242424] rounded-full flex items-center justify-center mx-auto mb-4">
              <Film className="w-12 h-12 text-[#B3B3B3]" />
            </div>
            <h3 className="text-xl font-bold text-white mb-2">No Movies Yet</h3>
            <p className="text-[#B3B3B3] mb-6 font-light max-w-xs mx-auto">
              Start adding movies to this list to build your collection
            </p>
            <motion.div whileTap={{ scale: 0.98 }}>
              <Button
                onClick={() => navigate("/list-selection")}
                className="h-12 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-2xl px-8 shadow-lg shadow-[#E50914]/30"
              >
                <Plus className="w-5 h-5 mr-2" />
                Add Movies
              </Button>
            </motion.div>
          </motion.div>
        )}
      </div>
    </div>
  );
}
