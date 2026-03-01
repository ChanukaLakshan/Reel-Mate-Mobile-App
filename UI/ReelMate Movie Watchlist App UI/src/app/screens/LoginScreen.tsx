import { useState } from "react";
import { useNavigate } from "react-router";
import { Film, Mail, Lock } from "lucide-react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";

export function LoginScreen() {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(true);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    navigate("/home");
  };

  return (
    <div className="min-h-screen bg-[#121212] flex items-center justify-center p-4">
      <div className="w-full max-w-[360px]">
        {/* Logo and Branding */}
        <div className="text-center mb-12">
          <div className="inline-flex items-center justify-center w-20 h-20 rounded-full bg-[#E50914] mb-4 shadow-lg shadow-[#E50914]/30">
            <Film className="w-10 h-10 text-white" />
          </div>
          <h1 className="text-4xl font-bold text-white mb-2">ReelMate</h1>
          <p className="text-[#B3B3B3] font-light">Your Personal Movie Companion</p>
        </div>

        {/* Login Form */}
        <div className="bg-[#1E1E1E] rounded-2xl p-8 shadow-xl">
          <h2 className="text-2xl font-bold text-white mb-6">
            {isLogin ? "Welcome Back" : "Join ReelMate"}
          </h2>

          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <div className="relative">
                <Mail className="absolute left-4 top-1/2 transform -translate-y-1/2 w-5 h-5 text-[#B3B3B3]" />
                <Input
                  type="email"
                  placeholder="Email address"
                  className="pl-12 h-14 bg-[#242424] border-[#2A2A2A] text-white placeholder:text-[#B3B3B3] rounded-2xl focus:border-[#E50914] focus:ring-[#E50914]"
                />
              </div>
            </div>

            <div>
              <div className="relative">
                <Lock className="absolute left-4 top-1/2 transform -translate-y-1/2 w-5 h-5 text-[#B3B3B3]" />
                <Input
                  type="password"
                  placeholder="Password"
                  className="pl-12 h-14 bg-[#242424] border-[#2A2A2A] text-white placeholder:text-[#B3B3B3] rounded-2xl focus:border-[#E50914] focus:ring-[#E50914]"
                />
              </div>
            </div>

            {isLogin && (
              <div className="text-right">
                <button
                  type="button"
                  className="text-[#F5C518] text-sm font-light hover:underline"
                >
                  Forgot Password?
                </button>
              </div>
            )}

            <Button
              type="submit"
              className="w-full h-14 bg-[#E50914] hover:bg-[#B20710] text-white font-semibold rounded-2xl shadow-lg shadow-[#E50914]/30 transition-all"
            >
              {isLogin ? "Login" : "Create Account"}
            </Button>
          </form>

          <div className="mt-6 text-center">
            <p className="text-[#B3B3B3] text-sm font-light">
              {isLogin ? "Don't have an account? " : "Already have an account? "}
              <button
                onClick={() => setIsLogin(!isLogin)}
                className="text-[#E50914] font-medium hover:underline"
              >
                {isLogin ? "Register" : "Login"}
              </button>
            </p>
          </div>
        </div>

        {/* Footer */}
        <div className="mt-8 text-center">
          <p className="text-[#B3B3B3] text-xs font-light">
            By continuing, you agree to our Terms of Service and Privacy Policy
          </p>
        </div>
      </div>
    </div>
  );
}
