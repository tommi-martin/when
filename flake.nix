{
  description = "When-scheduler development shell";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }: 
    flake-utils.lib.eachDefaultSystem 
      (system:
        let
        javaVersion = 21;
        overlays = [
          (final: prev: rec {
            jdk = prev."jdk${toString javaVersion}_headless";
            clojure = prev.clojure.override { inherit jdk; };
          })
        ];
          pkgs = import nixpkgs { inherit system overlays; };
          buildInputs = with pkgs; [ go-task clojure nodejs postgresql];
        in
        with pkgs;
        {
          devShells.default = mkShell {
            venvDir = "venv";
            inherit buildInputs;
          };
        }
      );
}
