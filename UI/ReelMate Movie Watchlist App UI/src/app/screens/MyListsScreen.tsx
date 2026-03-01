import { useState } from "react";
import { useNavigate } from "react-router";
import { ArrowLeft, Plus, MoreVertical, Edit, Trash2, Share2, Film } from "lucide-react";
import { Button } from "../components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "../components/ui/dropdown-menu";
import { motion, AnimatePresence } from "motion/react";

const userLists = [
  {
    id: 1,
    name: "My Favorite Thrillers",
    description: "Edge-of-your-seat suspense movies",
    movieCount: 12,
    createdDate: "Jan 15, 2026",
    previews: [
      "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3ZpZSUyMHBvc3RlciUyMGNpbmVtYSUyMGRhcmt8ZW58MXx8fHwxNzcxODgwMDg3fDA&ixlib=rb-4.1.0&q=80&w=1080",
      "https://images.unsplash.com/photo-1563905463861-7d77975b3a44?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0aHJpbGxlciUyMHN1c3BlbnNlJTIwZGFya3xlbnwxfHx8fDE3NzE3NjI5MjB8MA&ixlib=rb-4.1.0&q=80&w=1080",
      "https://images.unsplash.com/photo-1767048264833-5b65aacd1039?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxob3Jyb3IlMjBtb3ZpZSUyMGF0bW9zcGhlcmV8ZW58MXx8fHwxNzcxODgwMDg4fDA&ixlib=rb-4.1.0&q=80&w=1080",
    ],
  },
  {
    id: 2,
    name: "Sci-Fi Masterpieces",
    description: "Mind-bending science fiction films",
    movieCount: 8,
    createdDate: "Jan 20, 2026",
    previews: [
      "https://images.unsplash.com/photo-1619960535209-fc795018bbe1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzY2ktZmklMjBmdXR1cmlzdGljJTIwc2NlbmV8ZW58MXx8fHwxNzcxODI1NTI5fDA&ixlib=rb-4.1.0&q=80&w=1080",
      "https://images.unsplash.com/photo-1645808651017-c5e3018553c7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBtb3ZpZSUyMHNjZW5lfGVufDF8fHx8MTc3MTg4MDA4N3ww&ixlib=rb-4.1.0&q=80&w=1080",
      "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3ZpZSUyMHBvc3RlciUyMGNpbmVtYSUyMGRhcmt8ZW58MXx8fHwxNzcxODgwMDg3fDA&ixlib=rb-4.1.0&q=80&w=1080",
    ],
  },
  {
    id: 3,
    name: "Romantic Getaways",
    description: "Love stories that warm the heart",
    movieCount: 15,
    createdDate: "Feb 1, 2026",
    previews: [
      "https://images.unsplash.com/photo-1708787788824-07d6d97b0111?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMG1vdmllJTIwY291cGxlfGVufDF8fHx8MTc3MTgyNDAxN3ww&ixlib=rb-4.1.0&q=80&w=1080",
      "https://images.unsplash.com/photo-1604674725989-52c312835516?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21lZHklMjBoYXBweSUyMHBlb3BsZXxlbnwxfHx8fDE3NzE4NDM3ODh8MA&ixlib=rb-4.1.0&q=80&w=1080",
      "https://images.unsplash.com/photo-1771502045792-232148a24dae?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkcmFtYSUyMHBlcnNvbiUyMGVtb3Rpb25hbHxlbnwxfHx8fDE3NzE4ODAwODl8MA&ixlib=rb-4.1.0&q=80&w=1080",
    ],
  },
];

export function MyListsScreen() {
  const navigate = useNavigate();
  const [lists, setLists] = useState(userLists);

  const handleDelete = (id: number) => {
    setLists((prev) => prev.filter((list) => list.id !== id));
  };

  return (
    <div className="min-h-screen bg-[#121212]">
      {/* Header */}
      <div className="sticky top-0 z-50 bg-[#121212]/95 backdrop-blur-sm border-b border-[#2A2A2A] px-4 py-4">
        <div className="max-w-[360px] mx-auto flex items-center justify-between">
          <div className="flex items-center gap-4">
            <button
              onClick={() => navigate("/home")}
              className="p-2 hover:bg-[#242424] rounded-full transition-colors"
            >
              <ArrowLeft className="w-6 h-6 text-white" />
            </button>
            <h1 className="text-lg font-semibold text-white">My Lists</h1>
          </div>
          <button
            onClick={() => navigate("/create-list")}
            className="p-2 hover:bg-[#242424] rounded-full transition-colors"
          >
            <Plus className="w-6 h-6 text-white" />
          </button>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 py-6">
        {/* Create New List Button */}
        <motion.div
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          whileTap={{ scale: 0.98 }}
        >
          <Button
            onClick={() => navigate("/create-list")}
            className="w-full h-14 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-2xl mb-6 shadow-lg shadow-[#E50914]/30"
          >
            <Plus className="w-5 h-5 mr-2" />
            Create New List
          </Button>
        </motion.div>

        {/* Lists */}
        {lists.length > 0 ? (
          <div className="space-y-4">
            <AnimatePresence>
              {lists.map((list, index) => (
                <motion.div
                  key={list.id}
                  initial={{ opacity: 0, x: -20 }}
                  animate={{ opacity: 1, x: 0 }}
                  exit={{ opacity: 0, x: 20, height: 0 }}
                  transition={{ duration: 0.3, delay: index * 0.05 }}
                  className="bg-[#1E1E1E] rounded-2xl overflow-hidden border border-[#2A2A2A] hover:border-[#E50914]/30 transition-all shadow-lg"
                >
                  {/* Preview Images */}
                  <div
                    onClick={() => navigate(`/list-detail/${list.id}`)}
                    className="cursor-pointer"
                  >
                    <div className="flex h-32 bg-[#242424]">
                      {list.previews.map((preview, idx) => (
                        <div
                          key={idx}
                          className="flex-1 relative overflow-hidden"
                        >
                          <img
                            src={preview}
                            alt=""
                            className="w-full h-full object-cover hover:scale-110 transition-transform duration-300"
                          />
                        </div>
                      ))}
                      {list.movieCount > 3 && (
                        <div className="absolute inset-0 bg-gradient-to-r from-transparent via-transparent to-[#1E1E1E]/80 pointer-events-none" />
                      )}
                    </div>
                  </div>

                  {/* List Info */}
                  <div className="p-4">
                    <div className="flex items-start justify-between mb-2">
                      <div
                        onClick={() => navigate(`/list-detail/${list.id}`)}
                        className="flex-1 cursor-pointer"
                      >
                        <h3 className="text-lg font-bold text-white mb-1">
                          {list.name}
                        </h3>
                        <p className="text-[#B3B3B3] text-sm font-light mb-2 line-clamp-1">
                          {list.description}
                        </p>
                      </div>

                      {/* Options Menu */}
                      <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                          <button className="p-2 hover:bg-[#242424] rounded-full transition-colors">
                            <MoreVertical className="w-5 h-5 text-[#B3B3B3]" />
                          </button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent
                          align="end"
                          className="bg-[#242424] border-[#2A2A2A] rounded-xl"
                        >
                          <DropdownMenuItem
                            onClick={() => navigate(`/list-detail/${list.id}`)}
                            className="text-white hover:bg-[#2A2A2A] cursor-pointer"
                          >
                            <Edit className="w-4 h-4 mr-2" />
                            Edit List
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            className="text-white hover:bg-[#2A2A2A] cursor-pointer"
                          >
                            <Share2 className="w-4 h-4 mr-2" />
                            Share List
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            onClick={() => handleDelete(list.id)}
                            className="text-[#EF4444] hover:bg-[#2A2A2A] cursor-pointer"
                          >
                            <Trash2 className="w-4 h-4 mr-2" />
                            Delete List
                          </DropdownMenuItem>
                        </DropdownMenuContent>
                      </DropdownMenu>
                    </div>

                    {/* Stats */}
                    <div className="flex items-center gap-4 text-sm">
                      <div className="flex items-center gap-1.5">
                        <Film className="w-4 h-4 text-[#F5C518]" />
                        <span className="text-[#B3B3B3] font-light">
                          {list.movieCount} {list.movieCount === 1 ? "movie" : "movies"}
                        </span>
                      </div>
                      <span className="text-[#2A2A2A]">•</span>
                      <span className="text-[#B3B3B3] font-light text-xs">
                        {list.createdDate}
                      </span>
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
            className="text-center py-16"
          >
            <div className="w-24 h-24 bg-[#1E1E1E] rounded-full flex items-center justify-center mx-auto mb-4 border border-[#2A2A2A]">
              <Film className="w-12 h-12 text-[#B3B3B3]" />
            </div>
            <h3 className="text-xl font-bold text-white mb-2">No Lists Yet</h3>
            <p className="text-[#B3B3B3] mb-6 font-light max-w-xs mx-auto">
              Create your first custom list to organize your favorite movies
            </p>
            <Button
              onClick={() => navigate("/create-list")}
              className="h-12 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-2xl px-8 shadow-lg shadow-[#E50914]/30"
            >
              <Plus className="w-5 h-5 mr-2" />
              Create Your First List
            </Button>
          </motion.div>
        )}
      </div>
    </div>
  );
}
