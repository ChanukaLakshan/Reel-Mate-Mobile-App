import { useState } from "react";
import { useNavigate } from "react-router";
import { ArrowLeft, Check } from "lucide-react";
import { Button } from "../components/ui/button";
import { motion, AnimatePresence } from "motion/react";

const movies = [
  {
    id: 1,
    title: "Midnight Shadows",
    poster: "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3ZpZSUyMHBvc3RlciUyMGNpbmVtYSUyMGRhcmt8ZW58MXx8fHwxNzcxODgwMDg3fDA&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2024",
  },
  {
    id: 2,
    title: "Beyond the Stars",
    poster: "https://images.unsplash.com/photo-1619960535209-fc795018bbe1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzY2ktZmklMjBmdXR1cmlzdGljJTIwc2NlbmV8ZW58MXx8fHwxNzcxODI1NTI5fDA&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2024",
  },
  {
    id: 3,
    title: "Love in Paris",
    poster: "https://images.unsplash.com/photo-1708787788824-07d6d97b0111?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMG1vdmllJTIwY291cGxlfGVufDF8fHx8MTc3MTgyNDAxN3ww&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2024",
  },
  {
    id: 4,
    title: "Dark Manor",
    poster: "https://images.unsplash.com/photo-1767048264833-5b65aacd1039?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxob3Jyb3IlMjBtb3ZpZSUyMGF0bW9zcGhlcmV8ZW58MXx8fHwxNzcxODgwMDg4fDA&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2023",
  },
  {
    id: 5,
    title: "The Last Stand",
    poster: "https://images.unsplash.com/photo-1645808651017-c5e3018553c7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBtb3ZpZSUyMHNjZW5lfGVufDF8fHx8MTc3MTg4MDA4N3ww&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2024",
  },
  {
    id: 6,
    title: "Laugh Out Loud",
    poster: "https://images.unsplash.com/photo-1604674725989-52c312835516?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21lZHklMjBoYXBweSUyMHBlb3BsZXxlbnwxfHx8fDE3NzE4NDM3ODh8MA&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2024",
  },
  {
    id: 7,
    title: "Emotional Journey",
    poster: "https://images.unsplash.com/photo-1771502045792-232148a24dae?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkcmFtYSUyMHBlcnNvbiUyMGVtb3Rpb25hbHxlbnwxfHx8fDE3NzE4ODAwODl8MA&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2024",
  },
  {
    id: 8,
    title: "Silent Night",
    poster: "https://images.unsplash.com/photo-1563905463861-7d77975b3a44?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0aHJpbGxlciUyMHN1c3BlbnNlJTIwZGFya3xlbnwxfHx8fDE3NzE3NjI5MjB8MA&ixlib=rb-4.1.0&q=80&w=1080",
    year: "2023",
  },
];

export function ListSelectionScreen() {
  const navigate = useNavigate();
  const [selectedMovies, setSelectedMovies] = useState<number[]>([]);

  const toggleSelection = (id: number) => {
    setSelectedMovies((prev) =>
      prev.includes(id) ? prev.filter((i) => i !== id) : [...prev, id]
    );
  };

  const handleAddToList = () => {
    if (selectedMovies.length > 0) {
      navigate("/list-detail/1");
    }
  };

  return (
    <div className="min-h-screen bg-[#121212] pb-24">
      {/* Header with Selection Count */}
      <div className="sticky top-0 z-50 bg-[#121212]/95 backdrop-blur-sm border-b border-[#2A2A2A] px-4 py-4">
        <div className="max-w-[360px] mx-auto flex items-center justify-between">
          <div className="flex items-center gap-4">
            <button
              onClick={() => navigate("/my-lists")}
              className="p-2 hover:bg-[#242424] rounded-full transition-colors"
            >
              <ArrowLeft className="w-6 h-6 text-white" />
            </button>
            <h1 className="text-lg font-semibold text-white">Select Movies</h1>
          </div>
          <AnimatePresence>
            {selectedMovies.length > 0 && (
              <motion.div
                initial={{ scale: 0 }}
                animate={{ scale: 1 }}
                exit={{ scale: 0 }}
                className="bg-[#E50914] text-white px-3 py-1.5 rounded-full text-sm font-semibold"
              >
                {selectedMovies.length} selected
              </motion.div>
            )}
          </AnimatePresence>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 py-6">
        {/* Info Banner */}
        <motion.div
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          className="bg-[#1E1E1E] rounded-2xl p-4 mb-6 border border-[#2A2A2A]"
        >
          <p className="text-[#B3B3B3] text-sm font-light text-center">
            Select movies to add to your list
          </p>
        </motion.div>

        {/* Movie Grid */}
        <div className="grid grid-cols-2 gap-4">
          {movies.map((movie, index) => {
            const isSelected = selectedMovies.includes(movie.id);
            return (
              <motion.div
                key={movie.id}
                initial={{ opacity: 0, scale: 0.9 }}
                animate={{ opacity: 1, scale: 1 }}
                transition={{ duration: 0.2, delay: index * 0.05 }}
                onClick={() => toggleSelection(movie.id)}
                className="relative cursor-pointer group"
              >
                {/* Movie Card */}
                <div
                  className={`bg-[#1E1E1E] rounded-2xl overflow-hidden transition-all duration-300 ${
                    isSelected
                      ? "ring-2 ring-[#E50914] shadow-lg shadow-[#E50914]/30"
                      : "hover:scale-105"
                  }`}
                >
                  <div className="aspect-[2/3] relative">
                    <img
                      src={movie.poster}
                      alt={movie.title}
                      className="w-full h-full object-cover"
                    />
                    {/* Checkbox Overlay */}
                    <div className="absolute top-2 right-2">
                      <motion.div
                        initial={false}
                        animate={{
                          scale: isSelected ? 1 : 0.8,
                          opacity: isSelected ? 1 : 0.7,
                        }}
                        className={`w-7 h-7 rounded-full flex items-center justify-center transition-colors ${
                          isSelected
                            ? "bg-[#E50914] shadow-lg shadow-[#E50914]/50"
                            : "bg-[#242424]/80 backdrop-blur-sm"
                        }`}
                      >
                        <AnimatePresence>
                          {isSelected && (
                            <motion.div
                              initial={{ scale: 0, rotate: -180 }}
                              animate={{ scale: 1, rotate: 0 }}
                              exit={{ scale: 0, rotate: 180 }}
                              transition={{ type: "spring", stiffness: 500, damping: 25 }}
                            >
                              <Check className="w-4 h-4 text-white" strokeWidth={3} />
                            </motion.div>
                          )}
                        </AnimatePresence>
                      </motion.div>
                    </div>

                    {/* Selection Overlay */}
                    {isSelected && (
                      <motion.div
                        initial={{ opacity: 0 }}
                        animate={{ opacity: 1 }}
                        exit={{ opacity: 0 }}
                        className="absolute inset-0 bg-[#E50914]/20"
                      />
                    )}
                  </div>

                  {/* Movie Info */}
                  <div className="p-3">
                    <h3 className="text-white font-medium text-sm mb-1 line-clamp-2">
                      {movie.title}
                    </h3>
                    <p className="text-[#B3B3B3] text-xs font-light">{movie.year}</p>
                  </div>
                </div>
              </motion.div>
            );
          })}
        </div>
      </div>

      {/* Floating Action Button */}
      <AnimatePresence>
        {selectedMovies.length > 0 && (
          <motion.div
            initial={{ y: 100, opacity: 0 }}
            animate={{ y: 0, opacity: 1 }}
            exit={{ y: 100, opacity: 0 }}
            transition={{ type: "spring", stiffness: 300, damping: 30 }}
            className="fixed bottom-6 left-0 right-0 px-4 z-50"
          >
            <div className="max-w-[360px] mx-auto">
              <motion.div whileTap={{ scale: 0.95 }}>
                <Button
                  onClick={handleAddToList}
                  className="w-full h-14 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-2xl shadow-2xl shadow-[#E50914]/50 text-base"
                >
                  Add {selectedMovies.length} {selectedMovies.length === 1 ? "Movie" : "Movies"} to List
                </Button>
              </motion.div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
}
