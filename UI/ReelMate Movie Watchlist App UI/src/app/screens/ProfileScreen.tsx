import { useNavigate } from "react-router";
import { ArrowLeft, Settings, Film, Star, Award, LogOut } from "lucide-react";
import { Button } from "../components/ui/button";

const watchedMovies = [
  {
    id: 1,
    poster: "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3ZpZSUyMHBvc3RlciUyMGNpbmVtYSUyMGRhcmt8ZW58MXx8fHwxNzcxODgwMDg3fDA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 2,
    poster: "https://images.unsplash.com/photo-1619960535209-fc795018bbe1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzY2ktZmklMjBmdXR1cmlzdGljJTIwc2NlbmV8ZW58MXx8fHwxNzcxODI1NTI5fDA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 3,
    poster: "https://images.unsplash.com/photo-1708787788824-07d6d97b0111?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMG1vdmllJTIwY291cGxlfGVufDF8fHx8MTc3MTgyNDAxN3ww&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 4,
    poster: "https://images.unsplash.com/photo-1767048264833-5b65aacd1039?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxob3Jyb3IlMjBtb3ZpZSUyMGF0bW9zcGhlcmV8ZW58MXx8fHwxNzcxODgwMDg4fDA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 5,
    poster: "https://images.unsplash.com/photo-1645808651017-c5e3018553c7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBtb3ZpZSUyMHNjZW5lfGVufDF8fHx8MTc3MTg4MDA4N3ww&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 6,
    poster: "https://images.unsplash.com/photo-1604674725989-52c312835516?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21lZHklMjBoYXBweSUyMHBlb3BsZXxlbnwxfHx8fDE3NzE4NDM3ODh8MA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 7,
    poster: "https://images.unsplash.com/photo-1771502045792-232148a24dae?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkcmFtYSUyMHBlcnNvbiUyMGVtb3Rpb25hbHxlbnwxfHx8fDE3NzE4ODAwODl8MA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 8,
    poster: "https://images.unsplash.com/photo-1563905463861-7d77975b3a44?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0aHJpbGxlciUyMHN1c3BlbnNlJTIwZGFya3xlbnwxfHx8fDE3NzE3NjI5MjB8MA&ixlib=rb-4.1.0&q=80&w=1080",
  },
];

export function ProfileScreen() {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-[#121212]">
      {/* Header */}
      <div className="sticky top-0 z-50 bg-[#121212]/95 backdrop-blur-sm border-b border-[#2A2A2A] px-4 py-4">
        <div className="max-w-[360px] mx-auto flex items-center justify-between">
          <button
            onClick={() => navigate("/home")}
            className="p-2 hover:bg-[#242424] rounded-full transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-white" />
          </button>
          <h1 className="text-lg font-semibold text-white">Profile</h1>
          <button className="p-2 hover:bg-[#242424] rounded-full transition-colors">
            <Settings className="w-6 h-6 text-white" />
          </button>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 py-6">
        {/* Profile Header */}
        <div className="text-center mb-8">
          {/* Avatar */}
          <div className="relative inline-block mb-4">
            <img
              src="https://images.unsplash.com/photo-1627729205753-52d2ddeefce1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwcm9maWxlJTIwbWFuJTIwcG9ydHJhaXR8ZW58MXx8fHwxNzcxODgwMDkwfDA&ixlib=rb-4.1.0&q=80&w=1080"
              alt="Profile"
              className="w-32 h-32 rounded-full object-cover border-4 border-[#E50914] shadow-xl shadow-[#E50914]/30"
            />
            <div className="absolute bottom-2 right-2 w-6 h-6 bg-[#22C55E] rounded-full border-4 border-[#121212]"></div>
          </div>

          {/* User Info */}
          <h2 className="text-2xl font-bold text-white mb-2">Alex Johnson</h2>
          <p className="text-[#B3B3B3] mb-1 font-light">@alexjohnson</p>
          <p className="text-[#B3B3B3] text-sm font-light max-w-xs mx-auto">
            Film enthusiast 🎬 | Lover of sci-fi and thrillers | Reviewing movies since 2020
          </p>
        </div>

        {/* Stats Cards */}
        <div className="grid grid-cols-3 gap-3 mb-8">
          <div className="bg-[#1E1E1E] rounded-2xl p-4 text-center border border-[#2A2A2A]">
            <Film className="w-6 h-6 text-[#E50914] mx-auto mb-2" />
            <div className="text-2xl font-bold text-white mb-1">142</div>
            <div className="text-[#B3B3B3] text-xs font-light">Watched</div>
          </div>
          <div className="bg-[#1E1E1E] rounded-2xl p-4 text-center border border-[#2A2A2A]">
            <Star className="w-6 h-6 text-[#FFD700] mx-auto mb-2" />
            <div className="text-2xl font-bold text-white mb-1">89</div>
            <div className="text-[#B3B3B3] text-xs font-light">Reviews</div>
          </div>
          <div className="bg-[#1E1E1E] rounded-2xl p-4 text-center border border-[#2A2A2A]">
            <Award className="w-6 h-6 text-[#F5C518] mx-auto mb-2" />
            <div className="text-2xl font-bold text-white mb-1">Sci-Fi</div>
            <div className="text-[#B3B3B3] text-xs font-light">Favorite</div>
          </div>
        </div>

        {/* Favorite Genres */}
        <div className="mb-8">
          <h3 className="text-lg font-bold text-white mb-3">Favorite Genres</h3>
          <div className="flex flex-wrap gap-2">
            {["Sci-Fi", "Thriller", "Action", "Drama", "Mystery"].map(
              (genre, index) => (
                <span
                  key={index}
                  className="bg-[#E50914]/20 text-[#E50914] px-4 py-2 rounded-full text-sm font-medium border border-[#E50914]/30"
                >
                  {genre}
                </span>
              )
            )}
          </div>
        </div>

        {/* Watched Movies Grid */}
        <div className="mb-8">
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-lg font-bold text-white">Watched Movies</h3>
            <span className="text-[#B3B3B3] text-sm">
              {watchedMovies.length} movies
            </span>
          </div>
          <div className="grid grid-cols-3 gap-2">
            {watchedMovies.map((movie) => (
              <div
                key={movie.id}
                className="aspect-[2/3] rounded-xl overflow-hidden cursor-pointer hover:scale-105 transition-transform shadow-lg"
              >
                <img
                  src={movie.poster}
                  alt={`Movie ${movie.id}`}
                  className="w-full h-full object-cover"
                />
              </div>
            ))}
          </div>
        </div>

        {/* Action Buttons */}
        <div className="space-y-3">
          <Button className="w-full h-12 bg-[#242424] text-white hover:bg-[#2A2A2A] rounded-2xl border border-[#2A2A2A]">
            <Settings className="w-5 h-5 mr-2" />
            Edit Profile
          </Button>
          <Button
            onClick={() => navigate("/")}
            className="w-full h-12 bg-[#1E1E1E] text-[#E50914] hover:bg-[#242424] rounded-2xl border border-[#E50914]/30"
          >
            <LogOut className="w-5 h-5 mr-2" />
            Logout
          </Button>
        </div>
      </div>
    </div>
  );
}
