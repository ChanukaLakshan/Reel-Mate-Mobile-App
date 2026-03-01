import { useState } from "react";
import { useNavigate, useParams } from "react-router";
import { ArrowLeft, Star, ThumbsUp } from "lucide-react";
import { Button } from "../components/ui/button";
import { Textarea } from "../components/ui/textarea";

const existingReviews = [
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
  {
    id: 3,
    user: "Emily Rodriguez",
    avatar: "https://images.unsplash.com/photo-1631885628966-a14af9faaa9b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwcm9maWxlJTIwd29tYW4lMjBwb3J0cmFpdHxlbnwxfHx8fDE3NzE4MzQwODl8MA&ixlib=rb-4.1.0&q=80&w=1080",
    rating: 5,
    comment: "A masterpiece! The acting was superb and the story was engaging from start to finish.",
    likes: 32,
    date: "1 week ago",
  },
];

export function ReviewScreen() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [rating, setRating] = useState(0);
  const [hoverRating, setHoverRating] = useState(0);
  const [review, setReview] = useState("");
  const [likedReviews, setLikedReviews] = useState<number[]>([]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (rating > 0 && review.trim()) {
      // Handle review submission
      navigate(`/movie/${id}`);
    }
  };

  const toggleLike = (reviewId: number) => {
    setLikedReviews((prev) =>
      prev.includes(reviewId)
        ? prev.filter((id) => id !== reviewId)
        : [...prev, reviewId]
    );
  };

  return (
    <div className="min-h-screen bg-[#121212]">
      {/* Header */}
      <div className="sticky top-0 z-50 bg-[#121212]/95 backdrop-blur-sm border-b border-[#2A2A2A] px-4 py-4">
        <div className="max-w-[360px] mx-auto flex items-center gap-4">
          <button
            onClick={() => navigate(`/movie/${id}`)}
            className="p-2 hover:bg-[#242424] rounded-full transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-white" />
          </button>
          <h1 className="text-lg font-semibold text-white">Write Review</h1>
        </div>
      </div>

      <div className="max-w-[360px] mx-auto px-4 py-6">
        {/* Review Form */}
        <div className="bg-[#1E1E1E] rounded-2xl p-6 mb-6 border border-[#2A2A2A]">
          <h2 className="text-xl font-bold text-white mb-4">Rate this movie</h2>

          {/* Star Rating Selector */}
          <div className="mb-6">
            <div className="flex justify-center gap-2 mb-2">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  key={star}
                  type="button"
                  onClick={() => setRating(star)}
                  onMouseEnter={() => setHoverRating(star)}
                  onMouseLeave={() => setHoverRating(0)}
                  className="transition-transform hover:scale-110"
                >
                  <Star
                    className={`w-12 h-12 ${
                      star <= (hoverRating || rating)
                        ? "text-[#FFD700]"
                        : "text-[#2A2A2A]"
                    }`}
                    fill={star <= (hoverRating || rating) ? "#FFD700" : "#2A2A2A"}
                  />
                </button>
              ))}
            </div>
            <p className="text-center text-[#B3B3B3] text-sm font-light">
              {rating > 0 ? `${rating} out of 5 stars` : "Tap to rate"}
            </p>
          </div>

          {/* Review Text Box */}
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="text-white mb-2 block">Your Review</label>
              <Textarea
                value={review}
                onChange={(e) => setReview(e.target.value)}
                placeholder="Share your thoughts about this movie..."
                className="min-h-[120px] bg-[#242424] border-[#2A2A2A] text-white placeholder:text-[#B3B3B3] rounded-xl focus:border-[#E50914] focus:ring-[#E50914] resize-none font-light"
              />
              <p className="text-[#B3B3B3] text-xs mt-2 font-light">
                {review.length} / 500 characters
              </p>
            </div>

            {/* Submit Button */}
            <Button
              type="submit"
              disabled={rating === 0 || !review.trim()}
              className="w-full h-12 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-2xl disabled:opacity-50 disabled:cursor-not-allowed shadow-lg shadow-[#E50914]/30"
            >
              Submit Review
            </Button>
          </form>
        </div>

        {/* User Reviews List */}
        <div>
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-xl font-bold text-white">All Reviews</h3>
            <span className="text-[#B3B3B3] text-sm">
              {existingReviews.length} reviews
            </span>
          </div>

          <div className="space-y-4">
            {existingReviews.map((userReview) => (
              <div
                key={userReview.id}
                className="bg-[#1E1E1E] rounded-2xl p-4 border border-[#2A2A2A] hover:border-[#E50914]/30 transition-colors"
              >
                <div className="flex items-start gap-3 mb-3">
                  <img
                    src={userReview.avatar}
                    alt={userReview.user}
                    className="w-12 h-12 rounded-full object-cover"
                  />
                  <div className="flex-1">
                    <div className="flex items-center justify-between mb-1">
                      <h4 className="text-white font-semibold">
                        {userReview.user}
                      </h4>
                      <span className="text-[#B3B3B3] text-xs">
                        {userReview.date}
                      </span>
                    </div>
                    <div className="flex items-center gap-1">
                      {[...Array(5)].map((_, i) => (
                        <Star
                          key={i}
                          className={`w-4 h-4 ${
                            i < userReview.rating
                              ? "text-[#FFD700]"
                              : "text-[#2A2A2A]"
                          }`}
                          fill={
                            i < userReview.rating ? "#FFD700" : "#2A2A2A"
                          }
                        />
                      ))}
                    </div>
                  </div>
                </div>
                <p className="text-[#B3B3B3] text-sm leading-relaxed mb-3 font-light">
                  {userReview.comment}
                </p>
                <button
                  onClick={() => toggleLike(userReview.id)}
                  className={`flex items-center gap-2 px-4 py-2 rounded-full text-sm transition-colors ${
                    likedReviews.includes(userReview.id)
                      ? "bg-[#E50914]/20 text-[#E50914]"
                      : "bg-[#242424] text-[#B3B3B3] hover:bg-[#2A2A2A]"
                  }`}
                >
                  <ThumbsUp
                    className="w-4 h-4"
                    fill={likedReviews.includes(userReview.id) ? "#E50914" : "none"}
                  />
                  <span>
                    {userReview.likes +
                      (likedReviews.includes(userReview.id) ? 1 : 0)}
                  </span>
                </button>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
