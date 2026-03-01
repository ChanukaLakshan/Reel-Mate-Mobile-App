import { useState } from "react";
import { useNavigate } from "react-router";
import { ArrowLeft } from "lucide-react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import { Textarea } from "../components/ui/textarea";
import { motion } from "motion/react";

export function CreateListScreen() {
  const navigate = useNavigate();
  const [listName, setListName] = useState("");
  const [description, setDescription] = useState("");

  const handleSave = () => {
    if (listName.trim()) {
      // Save list and navigate to selection mode
      navigate("/list-selection");
    }
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
          <h1 className="text-lg font-semibold text-white">Create Movie List</h1>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 py-6">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.3 }}
          className="bg-[#1E1E1E] rounded-2xl p-6 border border-[#2A2A2A] shadow-xl"
        >
          <h2 className="text-xl font-bold text-white mb-6">List Details</h2>

          {/* List Name Input */}
          <div className="mb-6">
            <label className="text-white mb-2 block font-medium">
              List Name <span className="text-[#E50914]">*</span>
            </label>
            <Input
              type="text"
              value={listName}
              onChange={(e) => setListName(e.target.value)}
              placeholder="e.g., My Favorite Thrillers"
              className="h-12 bg-[#242424] border-[#2A2A2A] text-white placeholder:text-[#B3B3B3] rounded-xl focus:border-[#E50914] focus:ring-[#E50914]"
              maxLength={50}
            />
            <p className="text-[#B3B3B3] text-xs mt-2 font-light">
              {listName.length} / 50 characters
            </p>
          </div>

          {/* Description Input */}
          <div className="mb-6">
            <label className="text-white mb-2 block font-medium">
              Description <span className="text-[#B3B3B3] font-light">(Optional)</span>
            </label>
            <Textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Add a description for your list..."
              className="min-h-[100px] bg-[#242424] border-[#2A2A2A] text-white placeholder:text-[#B3B3B3] rounded-xl focus:border-[#E50914] focus:ring-[#E50914] resize-none font-light"
              maxLength={200}
            />
            <p className="text-[#B3B3B3] text-xs mt-2 font-light">
              {description.length} / 200 characters
            </p>
          </div>

          {/* Action Buttons */}
          <div className="flex gap-3">
            <Button
              onClick={() => navigate("/my-lists")}
              className="flex-1 h-12 bg-[#242424] text-white hover:bg-[#2A2A2A] rounded-xl border border-[#2A2A2A]"
            >
              Cancel
            </Button>
            <motion.div
              className="flex-1"
              whileTap={{ scale: 0.98 }}
            >
              <Button
                onClick={handleSave}
                disabled={!listName.trim()}
                className="w-full h-12 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-xl disabled:opacity-50 disabled:cursor-not-allowed shadow-lg shadow-[#E50914]/30"
              >
                Save & Add Movies
              </Button>
            </motion.div>
          </div>
        </motion.div>

        {/* Info Card */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.3, delay: 0.1 }}
          className="mt-6 bg-[#1E1E1E]/50 rounded-2xl p-4 border border-[#2A2A2A]/50"
        >
          <p className="text-[#B3B3B3] text-sm font-light text-center">
            💡 Create custom lists to organize your favorite movies by genre, mood, or any theme you like!
          </p>
        </motion.div>
      </div>
    </div>
  );
}
