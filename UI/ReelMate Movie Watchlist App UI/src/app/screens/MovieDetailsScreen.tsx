import { useState } from "react";
import { useNavigate, useParams } from "react-router";
import { ArrowLeft, Plus, Star, Play, ThumbsUp, Check } from "lucide-react";
import { Button } from "../components/ui/button";

const movieData = {
  id: 1,
  title: "Midnight Shadows",
  poster: "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtb3ZpZSUyMHBvc3RlciUyMGNpbmVtYSUyMGRhcmt8ZW58MXx8fHwxNzcxODgwMDg3fDA&ixlib=rb-4.1.0&q=80&w=1080",
  genres: ["Thriller", "Mystery", "Drama"],
  runtime: "2h 15m",
  rating: 4.5,
  year: "2024",
  director: "Sofia Chen",
  description:
    "A gripping psychological thriller that follows a detective's journey into the depths of a mysterious case that blurs the line between reality and illusion. As shadows grow longer, the truth becomes harder to find.",
  cast: ["Emma Thompson", "John Rivera", "Michelle Park", "David Chen"],
};

const reviews = [
  {
    id: 1,
    user: "Sarah Miller",
    avatar: "https://images.unsplash.com/photo-1631885628966-a14af9faaa9b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwcm9maWxlJTIwd29tYW4lMjBwb3J0cmFpdHxlbnwxfHx8fDE3NzE4MzQwODl8MA&ixlib=rb-4.1.0&q=80&w=1080",
    rating: 5,
    comment: "Absolutely mind-blowing! The cinematography and plot twists kept me on the edge of my seat.",
    likes: 24,
    date: "2 days ago",
  },
  {
    id: 2,
    user: "Marcus Johnson",
    avatar: "https://images.unsplash.com/photo-1627729205753-52d2ddeefce1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwcm9maWxlJTIwbWFuJTIwcG9ydHJhaXR8ZW58MXx8fHwxNzcxODgwMDkwfDA&ixlib=rb-4.1.0&q=80&w=1080",
    rating: 4,
    comment: "Great movie with fantastic performances. Some parts were a bit slow but overall excellent.",
    likes: 18,
    date: "5 days ago",
  },
];

export function MovieDetailsScreen() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [inWatchlist, setInWatchlist] = useState(false);
  const [likedReviews, setLikedReviews] = useState<number[]>([]);

  const toggleLike = (reviewId: number) => {
    setLikedReviews((prev) =>
      prev.includes(reviewId)
        ? prev.filter((id) => id !== reviewId)
        : [...prev, reviewId]
    );
  };

  return (
    <div className="min-h-screen bg-[#121212]">
      {/* Header with Back Button */}
      <div className="sticky top-0 z-50 bg-[#121212]/95 backdrop-blur-sm border-b border-[#2A2A2A] px-4 py-4">
        <div className="max-w-[360px] mx-auto flex items-center gap-4">
          <button
            onClick={() => navigate("/home")}
            className="p-2 hover:bg-[#242424] rounded-full transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-white" />
          </button>
          <h1 className="text-lg font-semibold text-white truncate">Movie Details</h1>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto">
        {/* Poster Header */}
        <div className="relative h-96 overflow-hidden">
          <img
            src={movieData.poster}
            alt={movieData.title}
            className="w-full h-full object-cover"
          />
          <div className="absolute inset-0 bg-gradient-to-t from-[#121212] via-[#121212]/60 to-transparent"></div>
          
          {/* Play Trailer Button */}
          <button className="absolute bottom-6 left-1/2 transform -translate-x-1/2 flex items-center gap-2 bg-white/20 backdrop-blur-md text-white px-6 py-3 rounded-full hover:bg-white/30 transition-colors">
            <Play className="w-5 h-5" fill="white" />
            <span className="font-semibold">Watch Trailer</span>
          </button>
        </div>

        <div className="px-4 pb-6 -mt-12 relative z-10">
          {/* Movie Title and Info */}
          <div className="mb-6">
            <h2 className="text-3xl font-bold text-white mb-3">{movieData.title}</h2>
            <div className="flex items-center gap-3 mb-3">
              <div className="flex items-center gap-1">
                <Star className="w-5 h-5 text-[#FFD700]" fill="#FFD700" />
                <span className="text-white font-semibold">{movieData.rating}</span>
              </div>
              <span className="text-[#B3B3B3]">•</span>
              <span className="text-[#B3B3B3]">{movieData.year}</span>
              <span className="text-[#B3B3B3]">•</span>
              <span className="text-[#B3B3B3]">{movieData.runtime}</span>
            </div>
            <div className="flex flex-wrap gap-2">
              {movieData.genres.map((genre, index) => (
                <span
                  key={index}
                  className="bg-[#242424] text-[#B3B3B3] text-sm px-3 py-1 rounded-full"
                >
                  {genre}
                </span>
              ))}
            </div>
          </div>

          {/* Action Buttons */}
          <div className="flex gap-3 mb-6">
            <Button
              onClick={() => setInWatchlist(!inWatchlist)}
              className={`flex-1 h-12 rounded-2xl font-semibold ${
                inWatchlist
                  ? "bg-[#242424] text-white border border-[#2A2A2A] hover:bg-[#2A2A2A]"
                  : "bg-[#E50914] text-white hover:bg-[#B20710]"
              }`}
            >
              {inWatchlist ? (
                <>
                  <Check className="w-5 h-5 mr-2" />
                  In Watchlist
                </>
              ) : (
                <>
                  <Plus className="w-5 h-5 mr-2" />
                  Add to Watchlist
                </>
              )}
            </Button>
            <Button
              onClick={() => navigate(`/review/${id}`)}
              className="flex-1 h-12 bg-[#F5C518] text-[#121212] hover:bg-[#D4A817] rounded-2xl font-semibold"
            >
              Write Review
            </Button>
          </div>

          {/* Synopsis */}
          <div className="mb-6">
            <h3 className="text-xl font-bold text-white mb-3">Synopsis</h3>
            <p className="text-[#B3B3B3] leading-relaxed font-light">
              {movieData.description}
            </p>
          </div>

          {/* Director & Cast */}
          <div className="mb-6">
            <h3 className="text-xl font-bold text-white mb-3">Director</h3>
            <p className="text-[#B3B3B3] font-light">{movieData.director}</p>
          </div>

          <div className="mb-6">
            <h3 className="text-xl font-bold text-white mb-3">Cast</h3>
            <div className="flex flex-wrap gap-2">
              {movieData.cast.map((actor, index) => (
                <span
                  key={index}
                  className="bg-[#1E1E1E] text-[#B3B3B3] text-sm px-4 py-2 rounded-full border border-[#2A2A2A]"
                >
                  {actor}
                </span>
              ))}
            </div>
          </div>

          {/* Reviews Section */}
          <div>
            <div className="flex items-center justify-between mb-4">
              <h3 className="text-xl font-bold text-white">Reviews</h3>
              <span className="text-[#B3B3B3] text-sm">{reviews.length} reviews</span>
            </div>

            <div className="space-y-4">
              {reviews.map((review) => (
                <div
                  key={review.id}
                  className="bg-[#1E1E1E] rounded-2xl p-4 border border-[#2A2A2A]"
                >
                  <div className="flex items-start gap-3 mb-3">
                    <img
                      src={review.avatar}
                      alt={review.user}
                      className="w-10 h-10 rounded-full object-cover"
                    />
                    <div className="flex-1">
                      <div className="flex items-center justify-between mb-1">
                        <h4 className="text-white font-semibold">{review.user}</h4>
                        <span className="text-[#B3B3B3] text-xs">{review.date}</span>
                      </div>
                      <div className="flex items-center gap-1">
                        {[...Array(5)].map((_, i) => (
                          <Star
                            key={i}
                            className={`w-4 h-4 ${
                              i < review.rating ? "text-[#FFD700]" : "text-[#2A2A2A]"
                            }`}
                            fill={i < review.rating ? "#FFD700" : "#2A2A2A"}
                          />
                        ))}
                      </div>
                    </div>
                  </div>
                  <p className="text-[#B3B3B3] text-sm leading-relaxed mb-3 font-light">
                    {review.comment}
                  </p>
                  <button
                    onClick={() => toggleLike(review.id)}
                    className={`flex items-center gap-2 px-3 py-1.5 rounded-full text-sm transition-colors ${
                      likedReviews.includes(review.id)
                        ? "bg-[#E50914]/20 text-[#E50914]"
                        : "bg-[#242424] text-[#B3B3B3] hover:bg-[#2A2A2A]"
                    }`}
                  >
                    <ThumbsUp
                      className="w-4 h-4"
                      fill={likedReviews.includes(review.id) ? "#E50914" : "none"}
                    />
                    <span>{review.likes + (likedReviews.includes(review.id) ? 1 : 0)}</span>
                  </button>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
